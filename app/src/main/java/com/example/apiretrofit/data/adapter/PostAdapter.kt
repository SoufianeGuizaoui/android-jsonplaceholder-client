package com.example.apiretrofit.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.model.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val items = mutableListOf<Post>()

    fun submitList(data: List<Post>) {
        items.clear()
        items.addAll(data)  
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = items[position]
        holder.tvTitle.text = "${post.id}. ${post.title}"
        holder.tvBody.text = post.body
    }

    override fun getItemCount(): Int = items.size
}
