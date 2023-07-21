package sopt.uni.presentation.wish

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ActivityWishBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class WishActivity : BindingActivity<ActivityWishBinding>(R.layout.activity_wish) {
    private val wishViewModel: WishViewModel by viewModels()
    private val multiviewAdapter: WishMultiviewAdapter by lazy {
        WishMultiviewAdapter(this) { wishTypeId ->
            val intent = Intent(this, WishFcActivity::class.java)
            intent.putExtra(WISH_TYPE_ID, wishTypeId)
            startActivity(intent)
        }
    }
    private val yourMultiviewAdapter: YourWishMultiviewAdapter by lazy {
        YourWishMultiviewAdapter(this) { wishTypeId ->
            val intent = Intent(this, WishFcActivity::class.java)
            intent.putExtra(WISH_TYPE_ID, wishTypeId)
            startActivity(intent)
        }
    }

    private var _wishList = mutableListOf<WishMultiData>()

    private val userId = SparkleStorage.userId
    private val partnerId = SparkleStorage.partnerId

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
            if (wishViewModel.isMineState.value!!) {
                wishList.add(WishMultiData(0, wishViewModel.newWishCoupon.value))
            }

            for (i in 0 until it.size) {
                wishList.add(WishMultiData(1, wishCoupon = it[i]))
            }

            _wishList = wishList
            if (wishViewModel.isMineState.value!!) {
                multiviewAdapter.submitData(wishList)
            } else {
                yourMultiviewAdapter.submitData(wishList)
            }
        }

        with(binding) {
            val animator = rvWish.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
            tvWishMyWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishMyWish.setTextAppearance(R.style.Subtitle)
                tvWishYourWish.setTextAppearance(R.style.Body1_Regular)
                lifecycleScope.launch {
                    wishViewModel.getMyWishList(userId).join()
                }
                rvWish.adapter = multiviewAdapter
                rvWish.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                multiviewAdapter.submitData(_wishList)
            }
            tvWishYourWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishMyWish.setTextAppearance(R.style.Body1_Regular)
                tvWishYourWish.setTextAppearance(R.style.Subtitle)
                lifecycleScope.launch {
                    wishViewModel.getPartnerWishList(partnerId!!).join()
                }
                yourMultiviewAdapter.submitData(_wishList)
                rvWish.adapter = yourMultiviewAdapter
                rvWish.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
            btnWishBack.setOnClickListener {
                startActivity<HomeActivity>()
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        wishViewModel.setWishData(userId!!)
    }

    override fun onResume() {
        super.onResume()
        wishViewModel.setWishData(userId!!)
    }

    private fun initRecyclerView() {
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

    companion object {
        const val WISH_TYPE_ID = "WISH_TYPE_ID"
    }

    @Parcelize
    data class WishTypeId(val type: Int, val id: Int, val isUsed: Boolean, val isMine: Boolean) :
        Parcelable
}
