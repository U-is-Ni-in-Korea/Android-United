package sopt.uni.presentation.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import sopt.uni.R
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH1
import sopt.uni.data.entity.wish.MULTI_TYPE_WISH2
import sopt.uni.data.entity.wish.WishMultiData
import sopt.uni.databinding.FragmentWishMyWishBinding

class WishMyWishFragment : Fragment() {
    private var _binding: FragmentWishMyWishBinding? = null

    private val binding: FragmentWishMyWishBinding
        get() = requireNotNull(_binding) { "binding is null" }

    lateinit var multiviewAdapter: WishMultiviewAdapter
    val datas = mutableListOf<WishMultiData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishMyWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        multiviewAdapter = WishMultiviewAdapter(requireContext())
        binding.rvMyWish.adapter = multiviewAdapter

        binding.rvMyWish.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = multiviewAdapter
        }

        datas.apply {
            add(WishMultiData(MULTI_TYPE_WISH1, image = R.drawable.ic_onboarding, null, "green"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
            add(WishMultiData(MULTI_TYPE_WISH2, image = R.drawable.ic_apple, "맥북 사주기", "blue"))
        }
        multiviewAdapter.datas = datas
        multiviewAdapter.notifyDataSetChanged()
    }
}
