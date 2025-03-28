package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import ru.btpit.nmedia.model.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar

class CommunityActivity : AppCompatActivity() {
    private lateinit var postsContainer: LinearLayout
    private lateinit var scrollView: NestedScrollView
    private lateinit var videoLinkContainer: LinearLayout
    private var currentPostCount = 0
    private val postsPerLoad = 3
    private var isLoading = false
    private val dateFormat = SimpleDateFormat("d MMMM HH:mm", Locale("ru"))

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> {
                val millions = count / 1_000_000.0
                if (millions % 1 == 0.0) {
                    "${millions.toInt()}M"
                } else {
                    String.format("%.1fM", millions).replace(",", ".").trimEnd('0')
                        .trimEnd('.')
                }
            }
            count >= 10_000 -> {
                if (count % 1000 == 0) {
                    "${count / 1000}K"
                } else {
                    "${(count / 1000).toInt()}K"
                }
            }
            count >= 1_100 -> {
                String.format("%.1fK", count / 1000.0).replace(",", ".")
            }
            count >= 1_000 -> {
                "1K"
            }
            else -> count.toString()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        postsContainer = findViewById(R.id.postsContainer)
        scrollView = findViewById(R.id.scrollView)
        videoLinkContainer = findViewById(R.id.videoLinkContainer)

        // Обработка клика по ссылке на видео
        videoLinkContainer.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=qeQnMgega0k"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            startActivity(intent)
        }

        val subscribeButton: Button = findViewById(R.id.subscribeButton)
        val messageButton: Button = findViewById(R.id.messageButton)
        val backButton: ImageButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        subscribeButton.setOnClickListener {
            Toast.makeText(this, "Вы подписались на сообщество БТПИТ!", Toast.LENGTH_SHORT).show()
        }

        messageButton.setOnClickListener {
            Toast.makeText(this, "Открыть чат для сообщения", Toast.LENGTH_SHORT).show()
        }

        setupScrollListener()
        loadMorePosts()
    }

    private fun setupScrollListener() {
        scrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            val view = v.getChildAt(v.childCount - 1)
            val diff = view.bottom - (v.height + scrollY)
            
            if (diff <= 100 && !isLoading) {
                loadMorePosts()
            }
        }
    }

    private fun loadMorePosts() {
        isLoading = true
        for (i in 0 until postsPerLoad) {
            addPost()
        }
        isLoading = false
    }

    @SuppressLint("InflateParams")
    private fun addPost() {
        val postView = LayoutInflater.from(this).inflate(R.layout.item_post, null)
        
        // Сохраняем индекс текущего поста
        val currentIndex = currentPostCount
        
        // Настройка кнопок и счетчиков
        val likeButton = postView.findViewById<ImageButton>(R.id.like_button)
        val likeCountTextView = postView.findViewById<TextView>(R.id.like_count)
        val shareButton = postView.findViewById<ImageButton>(R.id.share_button)
        val shareCountTextView = postView.findViewById<TextView>(R.id.share_count)
        val viewCountTextView = postView.findViewById<TextView>(R.id.view_count)
        val commentCountTextView = postView.findViewById<TextView>(R.id.comment_count)
        val readMore = postView.findViewById<TextView>(R.id.read_more)
        val hideButton = postView.findViewById<TextView>(R.id.hide_button)
        val postDescription = postView.findViewById<TextView>(R.id.post_description)
        val postImage = postView.findViewById<ImageView>(R.id.post_image)
        val moreOptionsButton = postView.findViewById<ImageButton>(R.id.more_options)
        val publicationDate = postView.findViewById<TextView>(R.id.publication_date)

        // Установка даты публикации
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -currentIndex) // Каждый пост на день раньше
        val postDate = calendar.time
        publicationDate.text = dateFormat.format(postDate)

        // Установка изображения поста в зависимости от номера поста
        val imageIndex = currentIndex % 3
        when (imageIndex) {
            0 -> postImage.setImageResource(R.drawable.novost)
            1 -> postImage.setImageResource(R.drawable.novost2)
            2 -> postImage.setImageResource(R.drawable.novost3)
        }

        // Установка начальных значений
        var likeCount = when (imageIndex) {
            0 -> 51999
            1 -> 48999
            else -> 45999
        }
        var shareCount = when (imageIndex) {
            0 -> 520
            1 -> 480
            else -> 450
        }
        var viewCount = when (imageIndex) {
            0 -> 1300000
            1 -> 1250000
            else -> 1200000
        }
        var commentCount = when (imageIndex) {
            0 -> 1600
            1 -> 1450
            else -> 1300
        }
        var isLiked = false

        // Установка начальных значений в TextView с форматированием
        likeCountTextView.text = formatCount(likeCount)
        shareCountTextView.text = formatCount(shareCount)
        viewCountTextView.text = formatCount(viewCount)
        commentCountTextView.text = formatCount(commentCount)

        // Обработчики событий
        likeButton.setOnClickListener {
            isLiked = !isLiked
            if (isLiked) {
                likeCount++
                likeButton.setImageResource(R.drawable.likeactiv)
            } else {
                likeCount--
                likeButton.setImageResource(R.drawable.like)
            }
            likeCountTextView.text = formatCount(likeCount)
        }

        shareButton.setOnClickListener {
            shareCount++
            shareCountTextView.text = formatCount(shareCount)
        }

        readMore.setOnClickListener {
            postDescription.maxLines = Integer.MAX_VALUE
            readMore.visibility = View.GONE
            hideButton.visibility = View.VISIBLE
        }

        hideButton.setOnClickListener {
            postDescription.maxLines = 2
            readMore.visibility = View.VISIBLE
            hideButton.visibility = View.GONE
        }

        // Обработчик нажатия на кнопку с тремя точками
        moreOptionsButton.setOnClickListener {
            showPostOptionsDialog(postView, postDescription)
        }

        // Добавляем обработчик клика по посту
        postView.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java).apply {
                putExtra("post", Post(
                    id = currentIndex.toLong(),
                    author = "БТПИТ",
                    content = postDescription.text.toString(),
                    published = publicationDate.text.toString(),
                    likes = likeCount,
                    shares = shareCount,
                    views = viewCount,
                    comments = commentCount,
                    likedByMe = isLiked,
                    imageIndex = imageIndex
                ))
            }
            startActivityForResult(intent, currentIndex)
        }

        // Добавление поста в контейнер
        postsContainer.addView(postView)
        currentPostCount++
    }

    private fun showPostOptionsDialog(postView: View, postDescription: TextView) {
        val options = arrayOf("Редактировать", "Удалить")
        val dialog = AlertDialog.Builder(this)
            .setTitle("Выберите действие")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        showEditDialog(postDescription)
                    }
                    1 -> {
                        postsContainer.removeView(postView)
                        Toast.makeText(this, "Пост удален", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .create()

        // Применяем скругленные углы к диалогу
        dialog.window?.setBackgroundDrawableResource(android.R.drawable.dialog_holo_light_frame)
        dialog.show()
    }

    private fun showEditDialog(postDescription: TextView) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_post, null)
        val editText = dialogView.findViewById<EditText>(R.id.edit_text)
        
        // Установка текущего текста поста
        editText.setText(postDescription.text)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Редактировать пост")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val newText = editText.text.toString()
                if (newText.isNotEmpty()) {
                    postDescription.text = newText
                    Toast.makeText(this, "Пост отредактирован", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Отмена", null)
            .create()

        // Применяем скругленные углы к диалогу
        dialog.window?.setBackgroundDrawableResource(android.R.drawable.dialog_holo_light_frame)
        
        // Устанавливаем голубой цвет для кнопок
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(getColor(android.R.color.holo_blue_light))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(getColor(android.R.color.holo_blue_light))
        }
        
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val postId = data.getLongExtra("post_id", -1)
            val likes = data.getIntExtra("likes", 0)
            val shares = data.getIntExtra("shares", 0)
            val isLiked = data.getBooleanExtra("is_liked", false)
            val content = data.getStringExtra("content")

            // Обновляем данные в соответствующем посте
            val postView = postsContainer.getChildAt(postId.toInt())
            if (postView != null) {
                val likeButton = postView.findViewById<ImageButton>(R.id.like_button)
                val likeCountTextView = postView.findViewById<TextView>(R.id.like_count)
                val shareCountTextView = postView.findViewById<TextView>(R.id.share_count)
                val postDescription = postView.findViewById<TextView>(R.id.post_description)

                likeCountTextView.text = formatCount(likes)
                shareCountTextView.text = formatCount(shares)
                
                if (isLiked) {
                    likeButton.setImageResource(R.drawable.likeactiv)
                } else {
                    likeButton.setImageResource(R.drawable.like)
                }

                // Обновляем текст поста, если он был изменен
                content?.let {
                    postDescription.text = it
                }
            }
        }
    }
}
