package com.example.educationalplatform.ui.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import coil.load
import com.example.educationalplatform.databinding.FragmentStepBinding
import com.example.educationalplatform.view_model.StepViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StepFragment : Fragment() {

    private var stepId = 0

    private lateinit var binding: FragmentStepBinding
    private val viewModel: StepViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepBinding.inflate(inflater, container, false)

        stepId = arguments?.getInt(TopicFragment.stepIdKey)!!

        viewModel.loadSelectedStep(stepId)

        viewModel.selectedStep.observe(viewLifecycleOwner) { step ->
            binding.stepDescription.text = step.description
            configurePreviewImage(step.video.previewUrl)
            configureVideoView(step.video.videoUrl)
            binding.videoTitle.text = step.video.title
            binding.uploaded.text = step.video.uploaded
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
            binding.playButton.visibility = View.GONE
            binding.videoView.visibility = View.VISIBLE
        }
    }
}