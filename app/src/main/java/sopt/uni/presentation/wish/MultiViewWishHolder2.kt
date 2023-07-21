package sopt.uni.presentation.wish

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.uni.R
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishLargeBinding

class MultiViewWishHolder2(private val binding: ItemWishLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: WishMultiData,
        clickListener: (WishActivity.WishTypeId) -> Unit,
        context: Context,
    ) {
        with(binding) {
            val image = item.wishCoupon!!.image
            ivItemWl.load(image)
            tvItemWlTitle.text = item.wishCoupon.content
            if (item.wishCoupon.isUsed) {
                tvItemWlColor.text = context.getText(R.string.wish_coupon_gray)
                tvItemWlColor.setBackgroundResource(R.drawable.wish_ment_gray_rectangle)
                tvItemWlColor.setTextColor(context.getColor(R.color.Gray_400))
            } else {
                tvItemWlColor.text = context.getText(R.string.wish_coupon_green)
                tvItemWlColor.setTextColor(context.getColor(R.color.Green_600))
                tvItemWlColor.setBackgroundResource(R.drawable.wish_ment_green_rectangle)
            }

            root.setOnClickListener {
                clickListener(
                    WishActivity.WishTypeId(
                        item.type,
                        item.wishCoupon.id,
                        item.wishCoupon.isUsed,
                        isMine = true,
                    ),
                )
            }
        }
    }
}
