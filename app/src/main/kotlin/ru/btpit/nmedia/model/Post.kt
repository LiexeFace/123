package ru.btpit.nmedia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    @SerializedName("liked_by_me")
    var likedByMe: Boolean = false,
    @SerializedName("likes")
    var likes: Int = 0,
    @SerializedName("shares")
    var shares: Int = 0,
    @SerializedName("views")
    var views: Int = 0,
    @SerializedName("comments")
    var comments: Int = 0,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val imageIndex: Int = 0
) : Parcelable 