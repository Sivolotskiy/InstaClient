package com.instaclient.flow.tag_search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instaclient.R
import com.instaclient.model.Tag
import kotlinx.android.synthetic.main.item_tag.view.*

class TagAdapter (private val tags: MutableList<Tag>,
                  val onClick: (Tag) -> Unit)
    : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(tags[position])
    }

    override fun getItemCount(): Int = tags.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_tag, parent, false).let {
            ViewHolder(it, onClick)
        }
    }

    class ViewHolder(override val containerView: View, val onClick: (Tag) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(tag: Tag) {
            with(tag) {
                itemView.textViewTitle.text =itemView.context.getString(R.string.tag, name)
                itemView.textViewDescription.text = itemView.context.getString(R.string.public_posts,
                        tag.media_count)
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun addTags(newPosts: List<Tag>) {
        tags.clear()
        tags.addAll(newPosts)
    }

    interface LayoutContainer {
        val containerView: View?
    }

}