package com.manishk9.news99.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manishk9.news99.databinding.ItemHeadlineBinding
import com.manishk9.news99.entities.HeadLine

class HomeAdapter : androidx.recyclerview.widget.ListAdapter<HeadLine, HomeAdapter.HomeViewHolder>(Companion) {

    class HomeViewHolder(val binding: ItemHeadlineBinding) : RecyclerView.ViewHolder(binding.root)

    private var itemClickListener: ((HeadLine) -> Unit)? = null

    companion object : DiffUtil.ItemCallback<HeadLine>() {
        override fun areItemsTheSame(oldItem: HeadLine, newItem: HeadLine): Boolean =
            oldItem.title.equals(newItem.title)

        override fun areContentsTheSame(oldItem: HeadLine, newItem: HeadLine): Boolean =
            oldItem.title.equals(newItem.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       return HomeViewHolder(ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.headline = item
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }

    }

    fun setItemClickListener(listener: (item: HeadLine) -> Unit) {
        itemClickListener = listener
    }
}