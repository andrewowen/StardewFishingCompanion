package com.example.stardewfishingcompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FishAdapter(private val sharedViewModel: FishViewModel) :
    RecyclerView.Adapter<FishAdapter.FishViewHolder>() {

    class FishViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val fishTile: ConstraintLayout = view.findViewById(R.id.fish_tile)
        val fishName: TextView = view.findViewById(R.id.fish_name)
        val fishImage: ImageView = view.findViewById(R.id.fish_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fish_view, parent, false)

        return FishViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {

        val singleFish = sharedViewModel.fish.value?.get(position)

        if (singleFish != null) {
            holder.fishName.text = singleFish.name
            Glide.with(holder.itemView.context).load(singleFish.imgSrc).into(holder.fishImage)

            holder.fishTile.setOnClickListener {
                sharedViewModel.getSelectedFish(singleFish.id)
                println("SELECTED FISH ${singleFish.name}")
                val action = FishGridFragmentDirections.actionFishGridFragmentToFishDetailFragment(fishLabel = singleFish.name)
                holder.view.findNavController().navigate(action)
            }
        }


    }

    override fun getItemCount(): Int {
        return sharedViewModel.fish.value?.size ?: 0
    }

}