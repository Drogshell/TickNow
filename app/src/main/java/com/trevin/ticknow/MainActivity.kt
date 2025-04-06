package com.trevin.ticknow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.trevin.ticknow.data.Task
import com.trevin.ticknow.data.TaskDao
import com.trevin.ticknow.data.TickNowDatabase
import com.trevin.ticknow.databinding.ActivityMainBinding
import com.trevin.ticknow.databinding.DialogAddTaskBinding
import kotlin.concurrent.thread
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: TickNowDatabase
    private val taskDao: TaskDao by lazy { database.getTaskDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.pager.adapter = SwipePageAdapter(this)
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = "Tasks"
        }.attach()

        binding.fab.setOnClickListener {
            showAddTaskDialog()
        }

        database = TickNowDatabase.createDatabase(this)

    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.buttonShowDetails.setOnClickListener {
            dialogBinding.editTextTaskDetails.visibility =
                if (dialogBinding.editTextTaskDetails.isVisible) View.GONE else View.VISIBLE
        }
        dialogBinding.buttonSaveTask.setOnClickListener {
            val task = Task(
                title = dialogBinding.editTextTaskTitle.text.toString(),
                description = dialogBinding.editTextTaskDetails.text.toString()
            )
            thread {
                taskDao.createTask(task)
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    inner class SwipePageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun createFragment(position: Int): Fragment {
            return TasksFragment()
        }

        override fun getItemCount(): Int {
            return 1
        }
    }
}