package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ViewModel.SecondViewModel
import com.example.myapplication.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLiveData.setOnClickListener {
            viewModel.triggerLiveData()
        }
        binding.btnStateflow.setOnClickListener {
            viewModel.triggerStateFlow()
        }
        binding.btnFlow.setOnClickListener {
            // this returns the flow
            lifecycleScope.launchWhenStarted {
                viewModel.triggerFlow().collectLatest {
                    binding.tvFlowLabel.text = it
                }
            }
        }
        binding.btnSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }
        subscribeToObservable()
    }
    private fun subscribeToObservable() {
        //for liveDate
        viewModel.liveData.observe(this) {
            // we have to observe the liveData from the viewmodel
            binding.tvLiveDataLabel.text = it
        }

        //for stateFlow in Coroutine
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {
                binding.tvStateflowLabel.text = it
            }
        }

        // for sharedFlow
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                Snackbar.make(
                    binding.root,
                it,
                Snackbar.LENGTH_LONG).show()
            }
        }
    }
}