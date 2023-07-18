package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sopt.uni.databinding.FragmentWishUseYourBinding

class WishUseYourFragment : Fragment() {
    private var _binding: FragmentWishUseYourBinding? = null
    private val binding: FragmentWishUseYourBinding
        get() = requireNotNull(_binding) { "binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishUseYourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWishUseYourBack.setOnClickListener{
            activity?.finish()
        }
    }
}
