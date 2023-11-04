package ru.garshishka.testdelivery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testdelivery.R
import ru.garshishka.testdelivery.databinding.FragmentMainBinding
import ru.garshishka.testdelivery.ui.bannerPictures
import ru.garshishka.testdelivery.ui.viewholder.BannerPicListAdapter
import ru.garshishka.testdelivery.ui.viewholder.FoodListAdapter
import ru.garshishka.testdelivery.webapi.DataFeedState

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding: FragmentMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val viewModel:FoodViewModel by activityViewModels()

    private val adapter = FoodListAdapter()
    private val bannerAdapter = BannerPicListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribe()

        return binding.root
    }

    private fun bindSpinner(){
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.location,
                android.R.layout.simple_spinner_item
                ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            }
        }
    }

    private fun subscribe(){
        binding.apply {
            foodList.adapter = adapter
            bannerList.adapter = bannerAdapter
            loadingFailedButton.setOnClickListener {
                viewModel.load()
            }
        }
        bannerAdapter.submitList(bannerPictures)
        bindSpinner()

        viewModel.apply {
            foodData.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
            dataState.observe(viewLifecycleOwner){
                binding.loading.isVisible = it == DataFeedState.Loading
                binding.loadingFailedButton.isVisible = it == DataFeedState.Error
            }
        }
    }

}