package com.example.educationalplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.R
import com.example.educationalplatform.data.api.model.Topic

class TopicAdapter(private val selectListener: StepSelectListener) :
    ListAdapter<Topic, TopicAdapter.ViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Topic>() {
            override fun areContentsTheSame(
                oldItem: Topic,
                newItem: Topic
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.title)
        var itemId: TextView = itemView.findViewById(R.id.id)
        var itemSteps: RecyclerView = itemView.findViewById(R.id.step_recycler_view)

        fun bind(topic: Topic) {
            itemId.text = topic.id.toString()
            itemTitle.text = topic.title

            itemSteps.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = StepAdapter(selectListener)
            itemSteps.adapter = adapter
            adapter.submitList(topic.steps)
        }
    }

    override fun submitList(list: List<Topic>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}