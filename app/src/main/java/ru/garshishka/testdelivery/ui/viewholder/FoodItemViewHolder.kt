package ru.garshishka.testdelivery.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.garshishka.testdelivery.databinding.LayoutFoodItemBinding
import ru.garshishka.testdelivery.dto.Food

class FoodItemViewHolder(
    private val binding: LayoutFoodItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(food: Food){
        binding.apply {
            foodName.text = food.name
            foodDescription.text = food.description
            buyItemButton.text = "${food.price.toInt()} руб."
        }
    }
}

class FoodListAdapter():ListAdapter<Food,FoodItemViewHolder>(FoodDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val binding =
            LayoutFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val food = getItem(position) ?: return
        holder.bind(food)
    }

}

class FoodDiffCallback: DiffUtil.ItemCallback<Food>(){
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}