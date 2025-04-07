package com.trevin.ticknow.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trevin.ticknow.data.Task
import com.trevin.ticknow.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private lateinit var binding: FragmentTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Dummy code to see if the recycler view is working
        binding.recyclerView.adapter = TasksAdapter(
            tasks = listOf(
                Task(title = "first test task", description = "Fake dummy description"),
                Task(title = "second test task", description = "Fake dummy dummy description"),
                Task(title = "third test task", description = "Fake dummy dummy dummy description")
            )
        )
    }

}