package com.example.educationalplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.R
import com.example.educationalplatform.data.api.model.Step


class StepAdapter(private val selectListener: StepSelectListener) :
    ListAdapter<Step, StepAdapter.ViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Step>() {
            override fun areContentsTheSame(
                oldItem: Step,
                newItem: Step
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.step_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = getItem(position)
        holder.bind(step)
        holder.itemView.setOnClickListener {
            selectListener.getStep(step)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemId: TextView = itemView.findViewById(R.id.id)
        var itemDescription: TextView = itemView.findViewById(R.id.description)

        fun bind(step: Step) {
            itemId.text = step.id.toString()
            itemDescription.text = step.description
        }
    }

    override fun submitList(list: List<Step>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}

interface StepSelectListener {
    fun getStep(step: Step)
}