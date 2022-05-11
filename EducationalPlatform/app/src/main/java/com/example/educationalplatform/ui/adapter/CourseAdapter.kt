package com.example.educationalplatform.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.R
import com.example.educationalplatform.data.api.model.Course
import com.example.educationalplatform.globals.Constants

class CourseAdapter(private val selectListener: CourseSelectListener) :
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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.course_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var course = getItem(position)
        holder.bind(course)
        holder.itemView.setOnClickListener {
            selectListener.getCourse(course)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.title)
        var itemDescription: TextView = itemView.findViewById(R.id.description)
        var itemCreated: TextView = itemView.findViewById(R.id.created_time)
        var itemLikes: TextView = itemView.findViewById(R.id.likes)
        var itemCategory: TextView = itemView.findViewById(R.id.category)
        var itemPublisher: TextView = itemView.findViewById(R.id.publisher_name)

        fun bind(course: Course) {
            itemTitle.text = course.title
            itemDescription.text = course.description
            itemCreated.text = course.created
            itemLikes.text = course.likesCount.toString()
            itemCategory.text = course.category
            itemPublisher.text = course.publisherName
            if (course.isLiked) {
                itemLikes.setTextColor(Color.parseColor(Constants.RED_COLOR))
            }
        }
    }

    override fun submitList(list: List<Course>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}

interface CourseSelectListener {
    fun getCourse(course: Course)
}