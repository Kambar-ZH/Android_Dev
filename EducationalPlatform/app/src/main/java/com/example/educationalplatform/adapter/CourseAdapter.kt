package com.example.educationalplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.R
import com.example.educationalplatform.model.Course

class CourseAdapter():
    ListAdapter<Course, CourseAdapter.ViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Course>() {
            override fun areContentsTheSame(
                oldItem: Course,
                newItem: Course
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.course_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.title)
        var itemDescription: TextView = itemView.findViewById(R.id.description)
        var itemCreated: TextView = itemView.findViewById(R.id.created_time)
        var itemLikes: TextView = itemView.findViewById(R.id.likes)

        fun bind(course: Course) {
            itemTitle.text = course.title
            itemDescription.text = course.description
            itemCreated.text = course.created
            itemLikes.text = course.likes.toString()
        }
    }
    override fun submitList(list: List<Course>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}