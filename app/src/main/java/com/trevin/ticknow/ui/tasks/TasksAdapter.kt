package com.trevin.ticknow.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trevin.ticknow.data.Task
import com.trevin.ticknow.databinding.ItemTaskBinding

class TasksAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
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


    inner class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.textViewTitle.text = task.title
            binding.textViewDetails.text = task.description
        }
    }

}