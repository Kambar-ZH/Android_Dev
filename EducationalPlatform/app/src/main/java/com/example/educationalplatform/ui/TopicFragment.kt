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
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.R
import com.example.educationalplatform.adapter.StepSelectListener
import com.example.educationalplatform.adapter.TopicAdapter
import com.example.educationalplatform.data.api.TopicService
import com.example.educationalplatform.data.api.createCourseService
import com.example.educationalplatform.data.api.createTopicService
import com.example.educationalplatform.databinding.FragmentTopicBinding
import com.example.educationalplatform.data.api.model.Step
import com.example.educationalplatform.data.db.AppDatabase
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.respository.TopicRepository
import com.example.educationalplatform.view_model.TopicViewModel
import com.example.educationalplatform.view_model.TopicViewModelFactory

class TopicFragment : Fragment(), StepSelectListener {
    companion object {
        const val stepIdKey = "stepId"
    }

    private var courseId = 0

    private lateinit var binding: FragmentTopicBinding
    private lateinit var adapter: TopicAdapter
    private lateinit var viewModel: TopicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopicBinding.inflate(inflater, container, false)

        configureAdapter()

        configureViewModel()

        courseId = arguments?.getInt(CourseFragment.courseIdKey)!!

        viewModel.loadTopicListByCourse(courseId)

        viewModel.topicList.observe(viewLifecycleOwner) { topicList ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.likeButton.visibility = View.VISIBLE
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            adapter.submitList(topicList)
        }

        viewModel.likedCourse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Thanks for support!", Toast.LENGTH_SHORT).show()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        binding.likeButton.setOnClickListener {
            viewModel.likeCourse(courseId)
        }

        return binding.root
    }

    private fun configureAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = TopicAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun configureViewModel() {
        val topicRepository = TopicRepository(service = createTopicService())
        val courseRepository = CourseRepository(service = createCourseService())
        val viewModelFactory = TopicViewModelFactory(topicRepository = topicRepository, courseRepository = courseRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TopicViewModel::class.java]
    }

    override fun getStep(step: Step) {
        val bundle = bundleOf(stepIdKey to step.id)
        findNavController().navigate(R.id.action_topicFragment_to_stepFragment, bundle)
    }
}