package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.btpit.nmedia.R

class MainActivity2 : AppCompatActivity() {
    private var likeCount = 51999
    private var commentCount = 1600
    private var shareCount = 520
    private var viewCount = 1300000
    private var isLiked = false

    private lateinit var likeButton: ImageButton
    private lateinit var likeCountTextView: TextView
    private lateinit var shareButton: ImageButton
    private lateinit var shareCountTextView: TextView
    private lateinit var viewCountTextView: TextView
    private lateinit var commentCountTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val mainLayout = findViewById<View>(R.id.main)
        if (mainLayout == null) {
            Log.e("MainActivity2", "mainLayout is null")
        } else {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        likeButton = findViewById(R.id.like_button)
        likeCountTextView = findViewById(R.id.like_count)
        commentCountTextView = findViewById(R.id.comment_count)
        shareButton = findViewById(R.id.share_button)
        shareCountTextView = findViewById(R.id.share_count)
        viewCountTextView = findViewById(R.id.view_count)

        setupListeners()
        updateUI()
    }

    private fun setupListeners() {
        likeButton.setOnClickListener {
            toggleLike()
        }

        shareButton.setOnClickListener {
            shareCount++
            updateUI()
        }
    }

    private fun toggleLike() {
        isLiked = !isLiked
        if (isLiked) {
            likeCount++
            likeButton.setImageResource(R.drawable.likeactiv)
        } else {
            likeCount--
            likeButton.setImageResource(R.drawable.like)
        }
        updateUI()
    }

    private fun updateUI() {
        likeCountTextView.text = formatCount(likeCount)
        commentCountTextView.text = formatCount(commentCount)
        shareCountTextView.text = formatCount(shareCount)
        viewCountTextView.text = formatCount(viewCount)
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
                    "${
                        (count / 1000).toInt()
                    }K"
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
