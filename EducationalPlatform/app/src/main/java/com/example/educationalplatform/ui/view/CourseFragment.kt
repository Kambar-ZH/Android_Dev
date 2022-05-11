package com.example.educationalplatform.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationalplatform.R
import com.example.educationalplatform.ui.adapter.CourseAdapter
import com.example.educationalplatform.ui.adapter.CourseSelectListener
import com.example.educationalplatform.databinding.FragmentCourseBinding
import com.example.educationalplatform.data.api.model.Course
import com.example.educationalplatform.view_model.CourseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseFragment : Fragment(), CourseSelectListener {
    companion object {
        const val courseIdKey = "courseId"
    }

    private lateinit var binding: FragmentCourseBinding
    private lateinit var adapter: CourseAdapter
    private val viewModel: CourseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourseBinding.inflate(inflater, container, false)

        configureAdapter()

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

    override fun getCourse(course: Course) {
        val bundle = bundleOf(courseIdKey to course.id)
        findNavController().navigate(R.id.action_courseFragment_to_topicFragment, bundle)
    }
}