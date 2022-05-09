package com.example.educationalplatform.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationalplatform.R
import com.example.educationalplatform.adapter.CourseAdapter
import com.example.educationalplatform.adapter.CourseSelectListener
import com.example.educationalplatform.data.api.createCourseService
import com.example.educationalplatform.databinding.FragmentCourseBinding
import com.example.educationalplatform.data.api.model.Course
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.view_model.CourseViewModel
import com.example.educationalplatform.view_model.CourseViewModelFactory

class CourseFragment : Fragment(), CourseSelectListener {
    companion object {
        const val courseIdKey = "courseId"
    }

    private lateinit var binding: FragmentCourseBinding
    private lateinit var adapter: CourseAdapter
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(inflater, container, false)

        configureAdapter()

        configureViewModel()

        viewModel.loadCourseList()

        viewModel.courseList.observe(viewLifecycleOwner) { courseList ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            adapter.submitList(courseList)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun configureAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = CourseAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun configureViewModel() {
        val service = createCourseService()
        val repository = CourseRepository(service)
        val viewModelFactory = CourseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CourseViewModel::class.java]
    }

    override fun getCourse(course: Course) {
        val bundle = bundleOf(courseIdKey to course.id)
        findNavController().navigate(R.id.action_courseFragment_to_topicFragment, bundle)
    }

}