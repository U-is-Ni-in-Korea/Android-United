package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sopt.uni.R
import sopt.uni.databinding.FragmentWishUseMyBinding

class WishUseMyFragment : Fragment() {
    private var _binding: FragmentWishUseMyBinding? = null
    private val binding: FragmentWishUseMyBinding
        get() = requireNotNull(_binding) { "binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishUseMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnWishUseMyFinish.setOnClickListener {
                useWishDialog()
            }

            btnWishUseMyBack.setOnClickListener {
                activity?.finish()
            }
        }
    }

    private fun useWishDialog() {
        UseWishDialogFragment().apply {
            titleText = this@WishUseMyFragment.resources.getString(R.string.use_wish_dialog_title)
            bodyText =
                this@WishUseMyFragment.resources.getString(R.string.use_wish_dialog_description)
            confirmButtonText = this@WishUseMyFragment.resources.getString(R.string.dialog_ok_text)
            dismissButtonText =
                this@WishUseMyFragment.resources.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                val fragment = WishUsedFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_wish_fc, fragment).commit()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(parentFragmentManager, "")
    }
}
