package com.example.educationalplatform.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.educationalplatform.data.api.StepService
import com.example.educationalplatform.data.api.createStepService
import com.example.educationalplatform.databinding.FragmentStepBinding
import com.example.educationalplatform.respository.StepRepository
import com.example.educationalplatform.view_model.StepViewModel
import com.example.educationalplatform.view_model.StepViewModelFactory

class StepFragment : Fragment() {

    private var stepId = 0

    private lateinit var binding: FragmentStepBinding
    private lateinit var viewModel: StepViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepBinding.inflate(inflater, container, false)

        configureViewModel()

        stepId = arguments?.getInt(TopicFragment.stepIdKey)!!

        viewModel.loadSelectedStep(stepId)

        viewModel.selectedStep.observe(viewLifecycleOwner) { step ->
            binding.stepDescription.text = step.description
            configurePreviewImage(step.video.previewUrl)
            configureVideoView(step.video.videoUrl)
            binding.videoTitle.text = step.video.title
            binding.videoViews.text = step.video.views.toString()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun configureVideoView(videoUrl: String) {
        val uri = Uri.parse(videoUrl)
        binding.videoView.setVideoURI(uri)
        val mediaController = MediaController(context)
        binding.videoView.setMediaController(mediaController)
    }

    private fun configurePreviewImage(previewUrl: String) {
        binding.videoPreviewImageView.load(previewUrl)
        binding.videoPreviewImageView.setOnClickListener {
            binding.videoPreviewImageView.visibility = View.GONE
            binding.videoView.visibility = View.VISIBLE
        }
    }

    private fun configureViewModel() {
        val service = createStepService()
        val repository = StepRepository(service)
        val viewModelFactory = StepViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[StepViewModel::class.java]
    }
}