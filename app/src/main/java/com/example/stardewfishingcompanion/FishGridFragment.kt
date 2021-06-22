package com.example.stardewfishingcompanion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.stardewfishingcompanion.databinding.FragmentFishGridBinding
import com.squareup.moshi.Moshi

class FishGridFragment : Fragment(R.layout.fragment_fish_detail) {

    private var _binding: FragmentFishGridBinding? = null

    private val binding get() = _binding!!
    private val moshi: Moshi = Moshi.Builder().build()

    private val sharedViewModel: FishViewModel by activityViewModels {ViewModelFactory(repository = StaticJsonFishRepository(requireContext(), moshi))}
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFishGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.adapter = FishAdapter(sharedViewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ViewModelFactory constructor(private val repository: FishRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>)
            : T = FishViewModel(repository) as T
}

