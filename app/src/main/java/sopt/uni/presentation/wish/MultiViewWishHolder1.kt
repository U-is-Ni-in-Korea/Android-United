package sopt.uni.presentation.wish

import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishSmallBinding

class MultiViewWishHolder1(private val binding: ItemWishSmallBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WishMultiData) {
        with(binding) {
            tvItemWlColor.text = item.color
        }
    }
}
