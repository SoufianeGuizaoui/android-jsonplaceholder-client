package com.example.apiretrofit.ui.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiretrofit.R
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.data.adapter.CommentsAdapter
import com.example.apiretrofit.data.model.Post
import kotlin.getValue
import com.example.apiretrofit.ui.viewmodel.CommentViewModel


class PostDetailsActivity : AppCompatActivity() {

    private val viewModel: CommentViewModel by viewModels()
    private val adapter = CommentsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val recyclerView = findViewById<RecyclerView>(R.id.commentsId)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentsAdapter()


        val tvId = findViewById<TextView>(R.id.tvId)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvBody = findViewById<TextView>(R.id.tvBody)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val id = intent.getIntExtra("POST_ID", -1)
        val title = intent.getStringExtra("POST_TITLE")
        val body = intent.getStringExtra("POST_BODY")



        tvId.text = "ID: $id"
        tvTitle.text = "Titre: $title"
        tvBody.text = "Contenu:\n\n$body"

        viewModel.fetchCommentsByPostId(id)

        viewModel.comments.observe(this) { comments ->
            adapter.submitList(comments)
        }
    }
}
