package com.example.stardewfishingcompanion

import android.content.Context
import com.example.stardewfishingcompanion.db.FishData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.buffer
import okio.source

class StaticJsonFishRepository(
    private val context: Context,
    moshi: Moshi
): FishRepository {
    private val listType = Types.newParameterizedType(List::class.java, FishData::class.java)
    private val adapter: JsonAdapter<List<FishData>> = moshi.adapter(listType)

    override fun getAllFishData() = context.assets.open("fish.json").source().buffer().use { source ->
        adapter.fromJson(source)!!
    }

    override fun getSelectedFish(fish: List<FishData>, fishId: Int): FishData {
        return fish.first {
            it.id == fishId
        }!!
    }

}
