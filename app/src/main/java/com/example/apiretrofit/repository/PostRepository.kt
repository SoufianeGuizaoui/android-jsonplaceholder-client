package com.example.apiretrofit.repository

import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.service.RetrofitInstance

class PostRepository {

    suspend fun getPosts(): List<Post>? {
        return try {
            RetrofitInstance.api.getPosts()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            RetrofitInstance.api.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }
}
