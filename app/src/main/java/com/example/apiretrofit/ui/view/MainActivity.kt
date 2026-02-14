package com.example.apiretrofit.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.data.adapter.PostAdapter
import com.example.apiretrofit.ui.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()
    private val adapter = PostAdapter()


    private var selectedPost: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPostId = findViewById<EditText>(R.id.etPostId)
        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvPostDetails = findViewById<TextView>(R.id.tvPostDetails)
        val rvPosts = findViewById<RecyclerView>(R.id.rvPosts)



        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = adapter

        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un ID valide."
                selectedPost = null
            }
        }

        viewModel.posts.observe(this) { posts ->
            adapter.submitList(posts)
            tvPostDetails.text =
                if (posts.isEmpty()) "Aucun post trouvé."
                else "Posts chargés : ${posts.size}"

            selectedPost = null
        }

        viewModel.post.observe(this) { post ->
            selectedPost = post

            tvPostDetails.text = post?.let {
                "ID: ${it.id}\nTitre: ${it.title}\nContenu: ${it.body}"
            } ?: "Aucun post trouvé."
        }


        tvPostDetails.setOnClickListener {
            selectedPost?.let { post ->
                val intent = Intent(this, PostDetailsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                intent.putExtra("POST_TITLE", post.title)
                intent.putExtra("POST_BODY", post.body)
                startActivity(intent)
            }
        }
    }
}
