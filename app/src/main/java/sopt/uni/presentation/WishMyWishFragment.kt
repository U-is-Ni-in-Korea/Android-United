package sopt.uni.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sopt.uni.databinding.FragmentWishMyWishBinding

class WishMyWishFragment : Fragment() {
    private var _binding: FragmentWishMyWishBinding? = null
    private val binding: FragmentWishMyWishBinding
        get() = requireNotNull(_binding) { "binding is null" }

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
    }
}
