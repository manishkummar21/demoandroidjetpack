package com.manishk9.news99.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.manishk9.news99.MainViewModel
import com.manishk9.news99.R
import com.manishk9.news99.adapter.BindAdapter.formatDateTime
import com.manishk9.news99.databinding.FragmentDetailedBinding
import com.manishk9.news99.entities.HeadLine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedFragment : Fragment() {

    private var _binding: FragmentDetailedBinding? = null
    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.main)
    private val binding get() = _binding!!
    val args: DetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHeadLineDetailsByID(args.id).observe(viewLifecycleOwner, Observer {
            setData(it)
        })

        binding.backarrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setData(it: HeadLine?) {
        it?.let {
            Glide.with(requireContext())
                .load(it.urlToImage)
                .centerCrop()
                .into(binding.image)
            binding.description.text = it.description
            binding.source.text = it.source.name
            binding.title.text = it.title
            binding.publishedDate.formatDateTime(it.publishedAt)

        }

    }


}