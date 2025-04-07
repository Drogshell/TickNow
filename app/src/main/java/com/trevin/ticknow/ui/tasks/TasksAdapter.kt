package com.trevin.ticknow.ui.tasks

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.trevin.ticknow.data.Task
import com.trevin.ticknow.databinding.ItemTaskBinding

class TasksAdapter(private val tasks: List<Task>, private val listener: TaskUpdatedListener) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.checkBox.isChecked = task.isComplete
            binding.toggleStar.isChecked = task.isStarred

            if (task.isComplete){
                binding.textViewTitle.paintFlags = binding.textViewTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.textViewDetails.paintFlags = binding.textViewTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.textViewTitle.text = task.title
            binding.textViewDetails.text = task.description

            binding.checkBox.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> {
                        task.copy(isComplete = true)
                    }
                    else -> {
                        task.copy(isComplete = false)
                    }
                }
                listener.onTaskUpdated(updatedTask)
            }

            binding.toggleStar.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> {
                        task.copy(isStarred = true)
                    }
                    else -> {
                        task.copy(isStarred = false)
                    }
                }
                listener.onTaskUpdated(updatedTask)
            }
        }
    }

    // Interfaces allow for communication between a recycler view and a fragment or activity
    interface TaskUpdatedListener {
        fun onTaskUpdated(task: Task)
    }
}