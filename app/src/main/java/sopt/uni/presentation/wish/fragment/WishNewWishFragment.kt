package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sopt.uni.databinding.FragmentWishNewWishBinding

class WishNewWishFragment : Fragment() {
    private var _binding: FragmentWishNewWishBinding? = null
    private val binding: FragmentWishNewWishBinding
        get() = requireNotNull(_binding) { "bindig is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishNewWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bgNewWish.setOnClickListener {
        }
    }
}
