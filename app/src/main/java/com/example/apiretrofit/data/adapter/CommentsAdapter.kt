package com.example.apiretrofit.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private val items = mutableListOf<Comment>()

    fun submitList(data: List<Comment>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = items[position]

        holder.tvName.text = "${comment.id}. ${comment.name}"
        holder.tvEmail.text = comment.email
        holder.tvBody.text = comment.body
    }

    override fun getItemCount(): Int = items.size
}
