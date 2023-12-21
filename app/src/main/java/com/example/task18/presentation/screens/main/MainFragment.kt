package com.example.task18.presentation.screens.main

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task18.core.base.BaseFragment
import com.example.task18.core.helper.Observers
import com.example.task18.databinding.FragmentMainBinding
import com.example.task18.presentation.adapter.PersonsRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), Observers {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: PersonsRecyclerViewAdapter by lazy { PersonsRecyclerViewAdapter() }
    override fun init() {
        binding.recyclerView.apply {
            adapter = this@MainFragment.adapter
        }
        observers()
    }

    override fun observers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->

                    binding.progressBar.isVisible = viewState.isLoading

                    adapter.submitData(viewState.personsList)

                    if (viewState.isError) {
                        Snackbar.make(
                            binding.root,
                            viewState.error?.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}