package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class CommunityActivity : AppCompatActivity() {
    private lateinit var postsContainer: LinearLayout
    private lateinit var scrollView: NestedScrollView
    private var currentPostCount = 0
    private val postsPerLoad = 3
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        postsContainer = findViewById(R.id.postsContainer)
        scrollView = findViewById(R.id.scrollView)

        val subscribeButton: Button = findViewById(R.id.subscribeButton)
        val messageButton: Button = findViewById(R.id.messageButton)

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

        // Установка изображения поста в зависимости от номера поста
        when (currentPostCount % 3) {
            0 -> postImage.setImageResource(R.drawable.novost)
            1 -> postImage.setImageResource(R.drawable.novost2)
            2 -> postImage.setImageResource(R.drawable.novost3)
        }

        // Установка начальных значений
        var likeCount = 52
        var shareCount = 52
        var viewCount = 52
        var commentCount = 52
        var isLiked = false

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
            likeCountTextView.text = likeCount.toString()
        }

        shareButton.setOnClickListener {
            shareCount++
            shareCountTextView.text = shareCount.toString()
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

        // Добавление поста в контейнер
        postsContainer.addView(postView)
        currentPostCount++
    }
}
