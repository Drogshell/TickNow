package com.trevin.ticknow.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.trevin.ticknow.data.model.Task
import com.trevin.ticknow.databinding.FragmentTasksBinding
import com.trevin.ticknow.ui.tasks.TasksViewModel
import kotlinx.coroutines.launch

class TasksFragment : Fragment(), TasksAdapter.TaskItemClickListener {

    private val viewModel: TasksViewModel by viewModels()
    private lateinit var binding: FragmentTasksBinding
    private val adapter = TasksAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        fetchAllTasks()
    }

    private fun fetchAllTasks() {
        lifecycleScope.launch {
            viewModel.fetchTasks().collect { tasks ->
                adapter.setTasks(tasks)
            }
        }
    }

    override fun onTaskUpdated(task: Task) {
        viewModel.updateTask(task)
    }

    override fun onTaskDeleted(task: Task) {
        viewModel.deleteTask(task)
    }

}