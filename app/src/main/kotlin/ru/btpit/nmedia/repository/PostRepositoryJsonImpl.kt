package ru.btpit.nmedia.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.btpit.nmedia.model.Post
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class PostRepositoryJsonImpl(private val context: Context) : PostRepository {
    private val gson = Gson()
    private val fileName = "posts.json"
    private val type = object : TypeToken<List<Post>>() {}.type

    private val file: File
        get() = File(context.filesDir, fileName)

    private var posts: List<Post> = emptyList()
        get() {
            if (!file.exists()) {
                return emptyList()
            }
            return try {
                FileReader(file).use { reader ->
                    gson.fromJson(reader, type)
                }
            } catch (e: Exception) {
                emptyList()
            }
        }

    private fun saveToFile() {
        try {
            FileWriter(file).use { writer ->
                gson.toJson(posts, writer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getAll(): List<Post> = posts

    override fun save(post: Post) {
        posts = posts.toMutableList().apply {
            val index = indexOfFirst { it.id == post.id }
            if (index != -1) {
                set(index, post)
            } else {
                add(post)
            }
        }
        saveToFile()
    }

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (!post.likedByMe) post.likes + 1 else post.likes - 1
                )
            } else {
                post
            }
        }
        saveToFile()
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shares = post.shares + 1)
            } else {
                post
            }
        }
        saveToFile()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        saveToFile()
    }

    override fun getById(id: Long): Post? = posts.find { it.id == id }
} 