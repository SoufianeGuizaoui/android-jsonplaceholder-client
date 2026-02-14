package com.example.apiretrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post

    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = repository.getPosts() ?: emptyList()
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            _post.value = repository.getPostById(postId)
        }
    }

}
