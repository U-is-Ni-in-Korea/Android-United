package sopt.uni.presentation.wish

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH1
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishLargeBinding
import sopt.uni.databinding.ItemWishSmallBinding

class WishMultiviewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datas = mutableListOf<WishMultiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MULTI_TYPE_WISH1 -> {
                val binding =
                    ItemWishSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MultiViewWishHolder1(binding)
            }

            else -> {
                val binding =
                    ItemWishLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MultiViewWishHolder2(binding)
            }
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun getItemViewType(position: Int): Int {
        return datas[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (datas[position].type) {
            MULTI_TYPE_WISH1 -> {
                (holder as MultiViewWishHolder1).bind(datas[position])
                holder.setIsRecyclable(false)
            }

            else -> {
                (holder as MultiViewWishHolder2).bind(datas[position])
                holder.setIsRecyclable(false)
            }
        }
    }
}
