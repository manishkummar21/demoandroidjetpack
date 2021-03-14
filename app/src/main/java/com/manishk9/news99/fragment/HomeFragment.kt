package com.manishk9.news99.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.manishk9.news99.MainViewModel
import com.manishk9.news99.R
import com.manishk9.news99.ResultWrapper
import com.manishk9.news99.adapter.HomeAdapter
import com.manishk9.news99.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.main)

    private var hasInitializedRootView: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null)
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            val adapter = HomeAdapter()
            initializeRecylerView()
            binding.headLineslist.adapter = adapter
            viewModel.getTopHeadlines().observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ResultWrapper.Loading -> binding.progressCircular.visibility = View.VISIBLE
                    is ResultWrapper.NetWorkError -> binding.progressCircular.visibility = View.GONE
                    is ResultWrapper.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        println("error")
                    }
                    is ResultWrapper.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        println("Success load the data")
                        adapter.submitList(it.data)
                    }
                }
            })

            adapter.setItemClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailedFragment(
                        it.headlineID
                    )
                )
            }
        }
    }

    private fun initializeRecylerView() {
        binding.headLineslist.setHasFixedSize(true)

        binding.headLineslist.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider_24dp)!!
        )

        binding.headLineslist.addItemDecoration(dividerItemDecoration)

    }
}