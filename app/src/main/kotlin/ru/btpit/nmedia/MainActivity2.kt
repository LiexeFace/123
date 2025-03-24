package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.content.Intent
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
    // Post 1 variables
    private var likeCount = 51999
    private var commentCount = 1600
    private var shareCount = 520
    private var viewCount = 1300000
    private var isLiked = false

    // Post 2 variables
    private var likeCount2 = 51999
    private var commentCount2 = 1600
    private var shareCount2 = 520
    private var viewCount2 = 1300000
    private var isLiked2 = false

    // Post 3 variables
    private var likeCount3 = 51999
    private var commentCount3 = 1600
    private var shareCount3 = 520
    private var viewCount3 = 1300000
    private var isLiked3 = false

    // Post 1 views
    private lateinit var likeButton: ImageButton
    private lateinit var likeCountTextView: TextView
    private lateinit var shareButton: ImageButton
    private lateinit var shareCountTextView: TextView
    private lateinit var viewCountTextView: TextView
    private lateinit var commentCountTextView: TextView
    private lateinit var avatarButton: ImageButton

    // Post 2 views
    private lateinit var likeButton2: ImageButton
    private lateinit var likeCountTextView2: TextView
    private lateinit var shareButton2: ImageButton
    private lateinit var shareCountTextView2: TextView
    private lateinit var viewCountTextView2: TextView
    private lateinit var commentCountTextView2: TextView
    private lateinit var avatarButton2: ImageButton

    // Post 3 views
    private lateinit var likeButton3: ImageButton
    private lateinit var likeCountTextView3: TextView
    private lateinit var shareButton3: ImageButton
    private lateinit var shareCountTextView3: TextView
    private lateinit var viewCountTextView3: TextView
    private lateinit var commentCountTextView3: TextView
    private lateinit var avatarButton3: ImageButton

    //Обработчик против ошибок логов
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

        //Обработчик события открытия и скрытия текста
        val readMore: TextView = findViewById(R.id.read_more)
        val postDescription: TextView = findViewById(R.id.post_description)
        val hideButton: TextView = findViewById(R.id.hide_button)

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

        // Post 2 text handlers
        val readMore2: TextView = findViewById(R.id.read_more2)
        val postDescription2: TextView = findViewById(R.id.post_description2)
        val hideButton2: TextView = findViewById(R.id.hide_button2)

        readMore2.setOnClickListener {
            postDescription2.maxLines = Integer.MAX_VALUE
            readMore2.visibility = View.GONE
            hideButton2.visibility = View.VISIBLE
        }

        hideButton2.setOnClickListener {
            postDescription2.maxLines = 2
            readMore2.visibility = View.VISIBLE
            hideButton2.visibility = View.GONE
        }

        // Post 3 text handlers
        val readMore3: TextView = findViewById(R.id.read_more3)
        val postDescription3: TextView = findViewById(R.id.post_description3)
        val hideButton3: TextView = findViewById(R.id.hide_button3)

        readMore3.setOnClickListener {
            postDescription3.maxLines = Integer.MAX_VALUE
            readMore3.visibility = View.GONE
            hideButton3.visibility = View.VISIBLE
        }

        hideButton3.setOnClickListener {
            postDescription3.maxLines = 2
            readMore3.visibility = View.VISIBLE
            hideButton3.visibility = View.GONE
        }

        // Initialize Post 1 views
        likeButton = findViewById(R.id.like_button)
        likeCountTextView = findViewById(R.id.like_count)
        commentCountTextView = findViewById(R.id.comment_count)
        shareButton = findViewById(R.id.share_button)
        shareCountTextView = findViewById(R.id.share_count)
        viewCountTextView = findViewById(R.id.view_count)
        avatarButton = findViewById(R.id.avatar)

        // Initialize Post 2 views
        likeButton2 = findViewById(R.id.like_button2)
        likeCountTextView2 = findViewById(R.id.like_count2)
        commentCountTextView2 = findViewById(R.id.comment_count2)
        shareButton2 = findViewById(R.id.share_button2)
        shareCountTextView2 = findViewById(R.id.share_count2)
        viewCountTextView2 = findViewById(R.id.view_count2)
        avatarButton2 = findViewById(R.id.avatar2)

        // Initialize Post 3 views
        likeButton3 = findViewById(R.id.like_button3)
        likeCountTextView3 = findViewById(R.id.like_count3)
        commentCountTextView3 = findViewById(R.id.comment_count3)
        shareButton3 = findViewById(R.id.share_button3)
        shareCountTextView3 = findViewById(R.id.share_count3)
        viewCountTextView3 = findViewById(R.id.view_count3)
        avatarButton3 = findViewById(R.id.avatar3)

        setupListeners()
        updateUI()
    }

    private fun setupListeners() {
        // Post 1 listeners
        likeButton.setOnClickListener {
            toggleLike()
        }

        shareButton.setOnClickListener {
            shareCount++
            updateUI()
        }
        avatarButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }

        // Post 2 listeners
        likeButton2.setOnClickListener {
            toggleLike2()
        }

        shareButton2.setOnClickListener {
            shareCount2++
            updateUI()
        }
        avatarButton2.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }

        // Post 3 listeners
        likeButton3.setOnClickListener {
            toggleLike3()
        }

        shareButton3.setOnClickListener {
            shareCount3++
            updateUI()
        }
        avatarButton3.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
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

    private fun toggleLike2() {
        isLiked2 = !isLiked2
        if (isLiked2) {
            likeCount2++
            likeButton2.setImageResource(R.drawable.likeactiv)
        } else {
            likeCount2--
            likeButton2.setImageResource(R.drawable.like)
        }
        updateUI()
    }

    private fun toggleLike3() {
        isLiked3 = !isLiked3
        if (isLiked3) {
            likeCount3++
            likeButton3.setImageResource(R.drawable.likeactiv)
        } else {
            likeCount3--
            likeButton3.setImageResource(R.drawable.like)
        }
        updateUI()
    }

    private fun updateUI() {
        // Update Post 1
        likeCountTextView.text = formatCount(likeCount)
        commentCountTextView.text = formatCount(commentCount)
        shareCountTextView.text = formatCount(shareCount)
        viewCountTextView.text = formatCount(viewCount)

        // Update Post 2
        likeCountTextView2.text = formatCount(likeCount2)
        commentCountTextView2.text = formatCount(commentCount2)
        shareCountTextView2.text = formatCount(shareCount2)
        viewCountTextView2.text = formatCount(viewCount2)

        // Update Post 3
        likeCountTextView3.text = formatCount(likeCount3)
        commentCountTextView3.text = formatCount(commentCount3)
        shareCountTextView3.text = formatCount(shareCount3)
        viewCountTextView3.text = formatCount(viewCount3)
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
