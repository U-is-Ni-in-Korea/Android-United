package sopt.uni.presentation.wish

import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishLargeBinding

class MultiViewWishHolder2(private val binding: ItemWishLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WishMultiData) {
        with(binding) {
            ivItemWl.load(item.image)
            tvItemWlTitle.text = item.title
            tvItemWlColor.text = item.color
        }
    }
}
