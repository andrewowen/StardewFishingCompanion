package com.example.stardewfishingcompanion

import com.example.stardewfishingcompanion.db.FishData

interface FishRepository {
    fun getAllFishData(): List<FishData>
    fun getSelectedFish(fish: List<FishData>, fishId: Int): FishData
}