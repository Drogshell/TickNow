package com.trevin.ticknow.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.trevin.ticknow.R
import com.trevin.ticknow.databinding.ActivityMainBinding
import com.trevin.ticknow.databinding.DialogAddTaskBinding
import com.trevin.ticknow.ui.tasks.TasksFragment
import com.trevin.ticknow.util.InputValidator

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val tasksFragment: TasksFragment = TasksFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            pager.adapter = SwipePageAdapter(this@MainActivity)
            TabLayoutMediator(tabs, pager) { tab, _ ->
                tab.text = "Tasks"
            }.attach()
            fab.setOnClickListener {
                showAddTaskDialog()
            }
            setContentView(root)
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showAddTaskDialog() {
        DialogAddTaskBinding.inflate(layoutInflater).apply {
            val dialog = BottomSheetDialog(this@MainActivity)
            dialog.setContentView(root)

            buttonSaveTask.isEnabled = false
            editTextTaskTitle.addTextChangedListener{ input ->
                buttonSaveTask.isEnabled = InputValidator.isValidInput(input)
            }

            buttonShowDetails.setOnClickListener {
                editTextTaskDetails.visibility =
                    if (editTextTaskDetails.isVisible) View.GONE else View.VISIBLE
            }
            buttonSaveTask.setOnClickListener {
                viewModel.createTask(title = editTextTaskTitle.text.toString(), description = editTextTaskDetails.text.toString())
                dialog.dismiss()
                tasksFragment.fetchAllTasks()
            }
            dialog.show()
        }
    }

    inner class SwipePageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun createFragment(position: Int): Fragment {
            return tasksFragment
        }

        override fun getItemCount(): Int {
            return 1
        }
    }
}