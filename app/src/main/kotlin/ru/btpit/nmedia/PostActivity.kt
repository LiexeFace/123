package ru.btpit.nmedia

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.model.Post

class PostActivity : AppCompatActivity() {
    private lateinit var post: Post
    private var likeCount: Int = 0
    private var shareCount: Int = 0
    private var isLiked: Boolean = false
    private lateinit var postDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        // Получаем данные поста из Intent
        post = intent.getParcelableExtra("post") ?: return finish()
        
        // Инициализируем счетчики
        likeCount = post.likes
        shareCount = post.shares
        isLiked = post.likedByMe

        setupViews()
    }

    private fun setupViews() {
        // Настройка кнопок и счетчиков
        val likeButton = findViewById<ImageButton>(R.id.like_button)
        val likeCountTextView = findViewById<TextView>(R.id.like_count)
        val shareButton = findViewById<ImageButton>(R.id.share_button)
        val shareCountTextView = findViewById<TextView>(R.id.share_count)
        val viewCountTextView = findViewById<TextView>(R.id.view_count)
        val commentCountTextView = findViewById<TextView>(R.id.comment_count)
        postDescription = findViewById(R.id.post_description)
        val postImage = findViewById<ImageView>(R.id.post_image)
        val moreOptionsButton = findViewById<ImageButton>(R.id.more_options)
        val publicationDate = findViewById<TextView>(R.id.publication_date)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        // Установка данных поста
        publicationDate.text = post.published
        postDescription.text = post.content

        // Установка изображения поста в зависимости от переданного индекса
        when (post.imageIndex) {
            0 -> postImage.setImageResource(R.drawable.novost)
            1 -> postImage.setImageResource(R.drawable.novost2)
            2 -> postImage.setImageResource(R.drawable.novost3)
        }

        // Установка начальных значений в TextView с форматированием
        likeCountTextView.text = formatCount(likeCount)
        shareCountTextView.text = formatCount(shareCount)
        viewCountTextView.text = formatCount(post.views)
        commentCountTextView.text = formatCount(post.comments)

        // Установка начального состояния кнопки лайка
        if (isLiked) {
            likeButton.setImageResource(R.drawable.likeactiv)
        }

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

        moreOptionsButton.setOnClickListener {
            showPostOptionsDialog()
        }

        backButton.setOnClickListener {
            // Возвращаем обновленные данные в CommunityActivity
            val resultIntent = Intent().apply {
                putExtra("post_id", post.id)
                putExtra("likes", likeCount)
                putExtra("shares", shareCount)
                putExtra("is_liked", isLiked)
                putExtra("content", postDescription.text.toString())
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun showPostOptionsDialog() {
        val options = arrayOf("Редактировать", "Удалить")
        val dialog = AlertDialog.Builder(this)
            .setTitle("Выберите действие")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        showEditDialog()
                    }
                    1 -> {
                        Toast.makeText(this, "Пост удален", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.drawable.dialog_holo_light_frame)
        dialog.show()
    }

    private fun showEditDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_post, null)
        val editText = dialogView.findViewById<android.widget.EditText>(R.id.edit_text)
        
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

        dialog.window?.setBackgroundDrawableResource(android.R.drawable.dialog_holo_light_frame)
        
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(getColor(android.R.color.holo_blue_light))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(getColor(android.R.color.holo_blue_light))
        }
        
        dialog.show()
    }

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
} 