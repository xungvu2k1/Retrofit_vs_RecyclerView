package com.example.retrofit_vs_recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_vs_recyclerview.R
import com.example.retrofit_vs_recyclerview.models.Comment

class CommentAdapter(val context : Context) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    private lateinit var mListComment :List<Comment>

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list : List<Comment>){
        this.mListComment = list
        notifyDataSetChanged()
    }

    class CommentViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvPostID : TextView = view.findViewById(R.id.tv_postId)
        val tvName : TextView = view.findViewById(R.id.tv_name_comment)
        val tvEmail : TextView = view.findViewById(R.id.tv_email_comment)
        val tvBody : TextView = view.findViewById(R.id.tv_body_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListComment.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.tvPostID.text = mListComment[position].postId.toString()
        holder.tvName.text = mListComment[position].name
        holder.tvEmail.text = mListComment[position].email
        holder.tvBody.text = mListComment[position].body

    }


}