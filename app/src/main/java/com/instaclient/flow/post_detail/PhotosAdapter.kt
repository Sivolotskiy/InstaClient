package com.instaclient.flow.post_detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instaclient.R
import com.instaclient.flow.CircleTransform
import com.instaclient.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PhotosAdapter(private val posts: MutableList<Post>,
                    val onClick: (Post) -> Unit)
    : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_post, parent, false).let {
            ViewHolder(it, onClick)
        }
    }

    class ViewHolder(private val containerView: View, val onClick: (Post) -> Unit)
        : RecyclerView.ViewHolder(containerView) {

        fun bindData(repository: Post) {
            with(repository) {
                Picasso.with(itemView.context)
                        .load(images.standard_resolution.url)
                        .into(itemView.postPhoto)
                Picasso.with(itemView.context)
                        .load(user.profile_picture)
                        .transform(CircleTransform())
                        .into(itemView.userImageView)
                itemView.nameViewTitle.text = user.full_name

                containerView.setOnClickListener { onClick(this) }
            }
        }
    }

    fun addPhotos(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
    }
}