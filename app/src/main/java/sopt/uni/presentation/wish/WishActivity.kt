package sopt.uni.presentation.wish

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import sopt.uni.R
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH1
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH2
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.ActivityWishBinding
import sopt.uni.util.binding.BindingActivity

class WishActivity : BindingActivity<ActivityWishBinding>(R.layout.activity_wish) {

    private lateinit var multiviewAdapter: WishMultiviewAdapter
    private val datas = mutableListOf<WishMultiData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()

        with(binding) {
            tvWishMyWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishMyWish.setTextAppearance(R.style.Subtitle)
                tvWishYourWish.setTextAppearance(R.style.Body1_Regular)
            }
            tvWishYourWish.setOnClickListener {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishMyWish.setTextAppearance(R.style.Body1_Regular)
                tvWishYourWish.setTextAppearance(R.style.Subtitle)
            }
            btnWishBack.setOnClickListener {
                // 화면 전환
            }
        }
    }

    private fun initRecyclerView() {
        multiviewAdapter = WishMultiviewAdapter(this) {
            val intent = Intent(this, WishFcActivity::class.java)
            startActivity(intent)
        }

        binding.rvWish.adapter = multiviewAdapter

        binding.rvWish.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = multiviewAdapter
        }

        datas.apply {
            add(WishMultiData(MULTI_TYPE_WISH1, null, null, "green"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
        }
        multiviewAdapter.submitData(datas)
        updateRecyclerViewVisibility()
    }

    private fun updateRecyclerViewVisibility() {
        if (datas.isEmpty()) {
            binding.rvWish.visibility = View.INVISIBLE
            binding.tvWishEmptyMy.visibility = View.VISIBLE
        } else {
            binding.rvWish.visibility = View.VISIBLE
            binding.tvWishEmptyMy.visibility = View.INVISIBLE
        }
    }

//    private fun navigateToWishNewWishFragment() {
//        val fragment = WishNewWishFragment()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fc_wish, fragment)
//            .addToBackStack(null)
//            .commit()
//    }
}
