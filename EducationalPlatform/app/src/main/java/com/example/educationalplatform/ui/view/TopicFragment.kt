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
import com.example.educationalplatform.ui.adapter.StepSelectListener
import com.example.educationalplatform.ui.adapter.TopicAdapter
import com.example.educationalplatform.databinding.FragmentTopicBinding
import com.example.educationalplatform.data.api.model.Step
import com.example.educationalplatform.view_model.CourseViewModel
import com.example.educationalplatform.view_model.TopicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicFragment : Fragment(), StepSelectListener {
    companion object {
        const val stepIdKey = "stepId"
    }

    private var courseId = 0

    private val topicViewModel: TopicViewModel by viewModel()
    private val courseViewModel: CourseViewModel by viewModel()

    private lateinit var binding: FragmentTopicBinding
    private lateinit var adapter: TopicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicBinding.inflate(inflater, container, false)

        configureAdapter()

        courseId = arguments?.getInt(CourseFragment.courseIdKey)!!

        topicViewModel.loadTopicListByCourse(courseId)

        topicViewModel.topicList.observe(viewLifecycleOwner) { topicList ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.likeButton.visibility = View.VISIBLE
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            adapter.submitList(topicList)
        }

        courseViewModel.likedCourse.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.thanks_for_support), Toast.LENGTH_SHORT)
                .show()
        }

        topicViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        binding.likeButton.setOnClickListener {
            courseViewModel.likeCourse(courseId)
        }

        return binding.root
    }

    private fun configureAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = TopicAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun getStep(step: Step) {
        val bundle = bundleOf(stepIdKey to step.id)
        findNavController().navigate(R.id.action_topicFragment_to_stepFragment, bundle)
    }
}