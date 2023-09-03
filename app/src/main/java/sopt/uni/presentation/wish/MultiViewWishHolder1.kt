package sopt.uni.presentation.wish

import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishSmallBinding

class MultiViewWishHolder1(private val binding: ItemWishSmallBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WishMultiData, clickListener: (WishActivity.WishTypeId) -> Unit) {
        binding.apply {
            tvItemWlColor.text = "새 소원권 ${item.newWishCoupon}개"
        }
        if (item.newWishCoupon != 0) {
            binding.root.setOnClickListener {
                clickListener(WishActivity.WishTypeId(item.type, -1, false, true))
            }
        }
    }
}
