package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sopt.uni.databinding.FragmentWishUsedBinding

class WishUsedFragment : Fragment() {
    private var _binding: FragmentWishUsedBinding? = null
    private val binding: FragmentWishUsedBinding
        get() = requireNotNull(_binding) { "binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishUsedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnWishUsedClose.setOnClickListener{
                activity?.finish()
            }
            btnWishUsedGoHome.setOnClickListener{
                // 홈 화면 전환O
            }
        }
    }
}
