package com.example.swifttask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.swifttask.databinding.ListDesignBinding

class NoteAdapter : ListAdapter<Note, ViewHolder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position).let {

            holder.binding.apply {

                TaskTitle.text = it.title
                TaskDetails.text = it.details
                DueDate.text = it.date
                DueTime.text = it.time

            }
        }

    }

    companion object {

        var comparator = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }
    }

}