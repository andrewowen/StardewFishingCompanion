package com.example.stardewfishingcompanion

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.stardewfishingcompanion.databinding.FragmentFishDetailBinding
import com.example.stardewfishingcompanion.db.FishData
import com.squareup.moshi.Moshi

class FishDetailFragment : Fragment() {

    companion object {
        const val SELECTED_FISH = "selectedFish"
    }

    private val moshi: Moshi = Moshi.Builder().build()

    private val sharedViewModel: FishViewModel by activityViewModels {ViewModelFactory(repository = StaticJsonFishRepository(requireContext(), moshi))}

    private lateinit var binding : FragmentFishDetailBinding

    private lateinit var selectedFish: LiveData<FishData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(sharedViewModel.selectedFish !== null) {
            selectedFish = sharedViewModel.selectedFish
            println("SELECTED FISH ${sharedViewModel.selectedFish.value?.name}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.actionBar?.title = selectedFish.value?.name.toString()
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFishDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fishNameView = binding.fishDetailName
        fishNameView.text = selectedFish.value?.name

        val fishImageView = binding.fishDetailImage
        Glide.with(view.context).load(selectedFish.value?.imgSrc).into(fishImageView)

        val fishDescView = binding.fishDetailDesc
        fishDescView.text = "\"${selectedFish.value?.description}\""


    }
}