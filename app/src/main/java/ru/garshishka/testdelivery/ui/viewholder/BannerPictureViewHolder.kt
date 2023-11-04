package ru.garshishka.testdelivery.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.garshishka.testdelivery.databinding.LayoutBannerPictureBinding

class BannerPictureViewHolder(
    private val binding: LayoutBannerPictureBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(resId: Int){
        binding.apply {
            bannerPicture.setBackgroundResource(resId)
        }
    }
}

class BannerPicListAdapter():ListAdapter<Int,BannerPictureViewHolder>(BannerPicDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPictureViewHolder {
        val binding =
            LayoutBannerPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerPictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerPictureViewHolder, position: Int) {
        val bannerPic = getItem(position) ?: return
        holder.bind(bannerPic)
    }

}

class BannerPicDiffCallback: DiffUtil.ItemCallback<Int>(){
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
}