package sopt.uni.presentation.wish.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentWishNewWishBinding
import sopt.uni.presentation.wish.CreateWishCouponViewModel

@AndroidEntryPoint
class WishNewWishFragment : Fragment() {
    private var _binding: FragmentWishNewWishBinding? = null
    private val binding: FragmentWishNewWishBinding
        get() = requireNotNull(_binding) { "bindig is null" }

    private val createWishCouponViewModel by viewModels<CreateWishCouponViewModel>()

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

        with(binding) {
            edtNewWishCv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    val text = s?.toString()
                    updateTextLength(text)
                }
            })
            btnNewWishClose.setOnClickListener {
                NewWishDialogFragment().show(childFragmentManager, "NewWishDialogFragment")
            }

            btnNewWishFinish.setOnClickListener {
                createWishCoupon(edtNewWishCv.text.toString())
                activity?.finish()
            }
        }
    }

    private fun updateTextLength(text: String?) {
        val trimmedText = text?.trim()
        val length = trimmedText?.length ?: 0
        val textLength = 54
        binding.tvNewWishContentLength.text = "$length/$textLength"

        if (length >= textLength) {
            binding.tvNewWishContentLength.setTextColor(Color.RED)
        } else {
            binding.tvNewWishContentLength.setTextColor(Color.GRAY)
        }

        val isTextNotEmpty = !trimmedText.isNullOrEmpty()
        val isLengthValid = length <= textLength

        binding.btnNewWishFinish.isEnabled = isTextNotEmpty || isLengthValid

        val backgroundTint = if (isTextNotEmpty && isLengthValid) {
            R.color.Lightblue_500
        } else {
            R.color.Gray_300
        }
        binding.btnNewWishFinish.backgroundTintList = resources.getColorStateList(backgroundTint)
    }

    private fun createWishCoupon(content: String) {
        createWishCouponViewModel.patchCreateWishCoupon(content)
    }
}
