package com.example.stardewfishingcompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stardewfishingcompanion.db.FishData

class FishViewModel(private val repository: FishRepository) : ViewModel() {

    private val _fish = MutableLiveData<List<FishData>>()
    val fish: LiveData<List<FishData>> = _fish

    private val _selectedFish = MutableLiveData<FishData>()
    val selectedFish: LiveData<FishData> = _selectedFish

    init {
       _fish.value = repository.getAllFishData()
    }

    fun getSelectedFish(fishId: Int) {
        _selectedFish.value = _fish.value?.let { repository.getSelectedFish(it, fishId) }
    }

}