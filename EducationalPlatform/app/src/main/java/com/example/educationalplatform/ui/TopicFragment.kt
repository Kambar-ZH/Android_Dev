package com.example.educationalplatform.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.educationalplatform.adapter.CourseAdapter
import com.example.educationalplatform.adapter.TopicAdapter
import com.example.educationalplatform.api.TopicService
import com.example.educationalplatform.api.createCourseService
import com.example.educationalplatform.databinding.FragmentTopicBinding
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.respository.TopicRepository
import com.example.educationalplatform.view_model.CourseViewModel
import com.example.educationalplatform.view_model.CourseViewModelFactory
import com.example.educationalplatform.view_model.TopicViewModel
import com.example.educationalplatform.view_model.TopicViewModelFactory

class TopicFragment : Fragment() {

    private lateinit var binding: FragmentTopicBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: TopicAdapter
    private lateinit var service: TopicService
    private lateinit var repository: TopicRepository
    private lateinit var viewModel: TopicViewModel
    private lateinit var viewModelFactory: TopicViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopicBinding.inflate(inflater, container, false)
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