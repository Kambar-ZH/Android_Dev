package com.example.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.dao.TodoDao
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.model.Todo
import com.google.gson.Gson

class TodoListFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var todoWithCategoryDao: TodoDao
    private lateinit var todos: List<Todo>
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("OK!!!!", "OK")
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MainApplication.instance.getDatabase()!!
        todoWithCategoryDao = db.todoDao()
        todos = todoWithCategoryDao.getAll()

        layoutManager = LinearLayoutManager(activity)
        Log.e("result", todos.toString())
        adapter = RecyclerAdapter(todos) {
            showTodoDetails(it)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun showTodoDetails(todo: Todo) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        var bundle = Bundle()
        bundle.putString("todo", Gson().toJson(todo))
        var todoDetailFragment = TodoDetailFragment()
        todoDetailFragment.arguments = bundle
        transaction?.replace(R.id.flFragment, todoDetailFragment)
        transaction?.addToBackStack("todo-details")
        transaction?.commit()
    }
}