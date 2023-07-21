package sopt.uni.presentation.wish

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH1
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ItemWishLargeBinding
import sopt.uni.databinding.ItemWishSmallBinding

class WishMultiviewAdapter(
    private val context: Context,
    private val changePageClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas = listOf<WishMultiData>()

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

    fun submitData(new: List<WishMultiData>) {
        datas = new.toList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MultiViewWishHolder1 -> {
                holder.bind(datas[position], changePageClickListener)
            }

            is MultiViewWishHolder2 -> {
                holder.bind(datas[position], changePageClickListener, context)
            }
        }
    }
}
