package com.example.apiretrofit.repository

import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.service.RetrofitInstance

class CommentRepository {

    suspend fun getCommentsByPostId(postId: Int): List<Comment>? {
        return try {
            RetrofitInstance.api.getCommentsByPostId(postId)
        } catch (e: Exception) {
            null
        }
    }
}
