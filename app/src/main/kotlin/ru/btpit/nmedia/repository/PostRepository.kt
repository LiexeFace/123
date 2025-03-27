package ru.btpit.nmedia.repository

import ru.btpit.nmedia.model.Post

interface PostRepository {
    fun getAll(): List<Post>
    fun save(post: Post)
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun getById(id: Long): Post?
} 