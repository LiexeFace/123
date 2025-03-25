package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.btpit.nmedia.R

class MainActivity2 : AppCompatActivity() {
    // Пост 1
    private var likeCount = 51999
    private var commentCount = 1600
    private var shareCount = 520
    private var viewCount = 1300000
    private var isLiked = false

    // Пост 2
    private var likeCount2 = 51999
    private var commentCount2 = 1600
    private var shareCount2 = 520
    private var viewCount2 = 1300000
    private var isLiked2 = false

    // Пост 3
    private var likeCount3 = 51999
    private var commentCount3 = 1600
    private var shareCount3 = 520
    private var viewCount3 = 1300000
    private var isLiked3 = false

    // Пост1
    private lateinit var likeButton: ImageButton
    private lateinit var likeCountTextView: TextView
    private lateinit var shareButton: ImageButton
    private lateinit var shareCountTextView: TextView
    private lateinit var viewCountTextView: TextView
    private lateinit var commentCountTextView: TextView
    private lateinit var avatarButton: ImageButton
    private lateinit var postDescription: TextView
    private lateinit var postContainer: View
    private lateinit var moreOptionsButton: ImageButton

    // Пост2
    private lateinit var likeButton2: ImageButton
    private lateinit var likeCountTextView2: TextView
    private lateinit var shareButton2: ImageButton
    private lateinit var shareCountTextView2: TextView
    private lateinit var viewCountTextView2: TextView
    private lateinit var commentCountTextView2: TextView
    private lateinit var avatarButton2: ImageButton
    private lateinit var postDescription2: TextView
    private lateinit var postContainer2: View
    private lateinit var moreOptionsButton2: ImageButton

    // Пост3
    private lateinit var likeButton3: ImageButton
    private lateinit var likeCountTextView3: TextView
    private lateinit var shareButton3: ImageButton
    private lateinit var shareCountTextView3: TextView
    private lateinit var viewCountTextView3: TextView
    private lateinit var commentCountTextView3: TextView
    private lateinit var avatarButton3: ImageButton
    private lateinit var postDescription3: TextView
    private lateinit var postContainer3: View
    private lateinit var moreOptionsButton3: ImageButton

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

        // Инцилизация пост1
        likeButton = findViewById(R.id.like_button)
        likeCountTextView = findViewById(R.id.like_count)
        commentCountTextView = findViewById(R.id.comment_count)
        shareButton = findViewById(R.id.share_button)
        shareCountTextView = findViewById(R.id.share_count)
        viewCountTextView = findViewById(R.id.view_count)
        avatarButton = findViewById(R.id.avatar)
        postDescription = findViewById(R.id.post_description)
        postContainer = findViewById(R.id.post_container)
        moreOptionsButton = findViewById(R.id.more_options)

        // Инцилизация пост2
        likeButton2 = findViewById(R.id.like_button2)
        likeCountTextView2 = findViewById(R.id.like_count2)
        commentCountTextView2 = findViewById(R.id.comment_count2)
        shareButton2 = findViewById(R.id.share_button2)
        shareCountTextView2 = findViewById(R.id.share_count2)
        viewCountTextView2 = findViewById(R.id.view_count2)
        avatarButton2 = findViewById(R.id.avatar2)
        postDescription2 = findViewById(R.id.post_description2)
        postContainer2 = findViewById(R.id.post_container2)
        moreOptionsButton2 = findViewById(R.id.more_options2)

        // Инцилизация пост3
        likeButton3 = findViewById(R.id.like_button3)
        likeCountTextView3 = findViewById(R.id.like_count3)
        commentCountTextView3 = findViewById(R.id.comment_count3)
        shareButton3 = findViewById(R.id.share_button3)
        shareCountTextView3 = findViewById(R.id.share_count3)
        viewCountTextView3 = findViewById(R.id.view_count3)
        avatarButton3 = findViewById(R.id.avatar3)
        postDescription3 = findViewById(R.id.post_description3)
        postContainer3 = findViewById(R.id.post_container3)
        moreOptionsButton3 = findViewById(R.id.more_options3)

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

        avatarButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }

        moreOptionsButton.setOnClickListener {
            showPostOptionsDialog(postContainer, postDescription)
        }


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

        moreOptionsButton2.setOnClickListener {
            showPostOptionsDialog(postContainer2, postDescription2)
        }


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

        moreOptionsButton3.setOnClickListener {
            showPostOptionsDialog(postContainer3, postDescription3)
        }


        setupTextHandlers()
    }

    private fun setupTextHandlers() {

        val readMore: TextView = findViewById(R.id.read_more)
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


        val readMore2: TextView = findViewById(R.id.read_more2)
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


        val readMore3: TextView = findViewById(R.id.read_more3)
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
    }

    private fun showPostOptionsDialog(postContainer: View, postDescription: TextView) {
        val options = arrayOf("Редактировать", "Удалить")
        AlertDialog.Builder(this)
            .setTitle("Выберите действие")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        showEditDialog(postDescription)
                    }
                    1 -> {
                        postContainer.visibility = View.GONE
                        Toast.makeText(this, "Пост удален", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }

    private fun showEditDialog(postDescription: TextView) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_post, null)
        val editText = dialogView.findViewById<EditText>(R.id.edit_text)
        
        // Установка текущего текста поста
        editText.setText(postDescription.text)

        AlertDialog.Builder(this)
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
            .show()
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

        likeCountTextView.text = formatCount(likeCount)
        commentCountTextView.text = formatCount(commentCount)
        shareCountTextView.text = formatCount(shareCount)
        viewCountTextView.text = formatCount(viewCount)


        likeCountTextView2.text = formatCount(likeCount2)
        commentCountTextView2.text = formatCount(commentCount2)
        shareCountTextView2.text = formatCount(shareCount2)
        viewCountTextView2.text = formatCount(viewCount2)


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
