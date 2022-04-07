package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todolist.dao.TodoDao
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.Todo
import retrofit2.Response
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.time.hours

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = MainApplication.instance.getDatabase()!!
        todoDao = db.todoDao()

        val todoListFragment = TodoListFragment(db, todoDao)
        Log.e("OK", "OK")
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, todoListFragment)
            commit()
        }
    }
}