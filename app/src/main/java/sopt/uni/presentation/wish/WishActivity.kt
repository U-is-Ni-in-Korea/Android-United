package sopt.uni.presentation.wish

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ActivityWishBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class WishActivity : BindingActivity<ActivityWishBinding>(R.layout.activity_wish) {
    private val wishViewModel: WishViewModel by viewModels()
    private lateinit var multiviewAdapter: WishMultiviewAdapter

    // private val userId = SparkleStorage.userId
    private val userId = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = wishViewModel
        initRecyclerView()
        wishViewModel.setWishData(userId!!)

        wishViewModel.wishCouponList.observe(this) {
            var wishList = mutableListOf<WishMultiData>()

            if (it.isEmpty() && !wishViewModel.isMineState.value!!) {
                binding.rvWish.visibility = View.INVISIBLE
                binding.tvWishEmptyMy.visibility = View.VISIBLE
                return@observe
            } else {
                binding.rvWish.visibility = View.VISIBLE
                binding.tvWishEmptyMy.visibility = View.INVISIBLE
            }
            if (wishViewModel.isMineState.value != false) {
                wishList.add(WishMultiData(0, wishViewModel.newWishCoupon.value))
            }

            for (i in 0 until it.size) {
                wishList.add(WishMultiData(1, wishCoupon = it[i]))
            }

            multiviewAdapter = WishMultiviewAdapter(this, ::changePageClickListener)
            binding.rvWish.adapter = multiviewAdapter

            binding.rvWish.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = multiviewAdapter
            }
            multiviewAdapter.submitData(wishList)
        }

        with(binding) {
            tvWishMyWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishMyWish.setTextAppearance(R.style.Subtitle)
                tvWishYourWish.setTextAppearance(R.style.Body1_Regular)
                wishViewModel.getMyWishList(userId)
            }
            tvWishYourWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishMyWish.setTextAppearance(R.style.Body1_Regular)
                tvWishYourWish.setTextAppearance(R.style.Subtitle)
                wishViewModel.getPartnerWishList(userId)
            }
            btnWishBack.setOnClickListener {
                startActivity<HomeActivity>()
            }
        }
    }

    private fun initRecyclerView() {
        multiviewAdapter = WishMultiviewAdapter(this) {
        }
        binding.rvWish.adapter = multiviewAdapter

        binding.rvWish.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = multiviewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvWish.adapter = null
    }

    fun changePageClickListener(position: Int) {
        val intent = Intent(this, WishFcActivity::class.java)

        if (position == 0) {
            intent.putExtra("wishCouponId", -1)
        } else {
            intent.putExtra("wishCouponId", wishViewModel.wishCouponList.value!![position].id)
        }
        startActivity(intent)
    }
}
