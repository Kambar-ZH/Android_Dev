package com.example.educationalplatform.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.adapter.CourseAdapter
import com.example.educationalplatform.api.CourseService
import com.example.educationalplatform.api.createCourseService
import com.example.educationalplatform.databinding.FragmentCourseBinding
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.view_model.CourseViewModel
import com.example.educationalplatform.view_model.CourseViewModelFactory

class CourseFragment : Fragment() {

    private lateinit var binding: FragmentCourseBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: CourseAdapter
    private lateinit var service: CourseService
    private lateinit var repository: CourseRepository
    private lateinit var viewModel: CourseViewModel
    private lateinit var viewModelFactory: CourseViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(activity)

        configureAdapter()

        configureViewModel()

        viewModel.loadCourseList()

        viewModel.courseList.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val courseList = response.body()
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyTextView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                adapter.submitList(courseList)
            } else {
                Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun configureAdapter() {
        binding.recyclerView.layoutManager = layoutManager
        adapter = CourseAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun configureViewModel() {
        service = createCourseService()
        repository = CourseRepository(service)
        viewModelFactory = CourseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CourseViewModel::class.java]
    }

}