package com.example.apiretrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.repository.CommentRepository
import com.example.apiretrofit.repository.PostRepository
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {

    private val repository = CommentRepository()

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments



    fun fetchCommentsByPostId(postId : Int) {
        viewModelScope.launch {
            _comments.value = repository.getCommentsByPostId(postId) ?: emptyList()
        }
    }

}
