package sopt.uni.presentation.wish.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentWishUseMyBinding
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.presentation.wish.WishFcViewModel
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class WishUseMyFragment : Fragment() {
    private var _binding: FragmentWishUseMyBinding? = null
    private val wishFcViewModel: WishFcViewModel by viewModels()
    private var wishCouponId: Int = 0
    private var wishCouponImage: String = ""
    private var wishCouponIsUsed: Boolean = true
    private val binding: FragmentWishUseMyBinding
        get() = requireNotNull(_binding) { "binding is null" }

    companion object {
        fun newInstance(wishCouponId: WishActivity.WishTypeId): WishUseMyFragment {
            val fragment = WishUseMyFragment()
            val args = Bundle()
            args.putInt("wishCouponId", wishCouponId.id)
            args.putBoolean("isUsed", wishCouponId.isUsed)
            fragment.arguments = args
            return fragment
        }
    }

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
        binding.vm = wishFcViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        val bundleId = arguments
        if (bundleId != null) {
            wishCouponId = bundleId.getInt("wishCouponId")
        }

        wishFcViewModel.setWishDetailData(wishCouponId)

        val bundleIsUsed = arguments
        if (bundleIsUsed != null) {
            wishCouponIsUsed = bundleIsUsed.getBoolean("isUsed")
        }

        with(binding) {
            if (wishCouponIsUsed == true) {
                btnWishUseMyFinish.isEnabled = false
                btnWishUseMyFinish.backgroundTintList =
                    resources.getColorStateList(R.color.Gray_300)
            } else {
                btnWishUseMyFinish.isEnabled = true
                btnWishUseMyFinish.backgroundTintList =
                    resources.getColorStateList(R.color.Lightblue_500)
            }

            btnWishUseMyFinish.setOnClickListener {
                useWishDialog()
            }

            btnWishUseMyBack.setOnClickListener {
                activity?.finish()
            }

            val image = wishCouponImage
            ivWishUseMy.load(image)
        }

        shareWishCoupon()
    }

    private fun useWishData() {
        wishFcViewModel.useWishData(wishCouponId)
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
                useWishData()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(parentFragmentManager, "")
    }

    private fun getBitmapFromWishCoupon(view: View, callback: (Bitmap?) -> Unit) {
        requireActivity().window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

            // 소원권 뷰 좌표를 계산해서 배열에 x,y 좌표를 반환
            val locationOfView = IntArray(2)
            view.getLocationInWindow(locationOfView)

            try {
                PixelCopy.request(
                    window,
                    Rect(
                        locationOfView[0],
                        locationOfView[1],
                        locationOfView[0] + view.width,
                        locationOfView[1] + view.height,
                    ),
                    bitmap,
                    { copyResult ->
                        if (copyResult == PixelCopy.SUCCESS) {
                            callback(bitmap)
                        } else {
                            callback.invoke(null)
                        }
                    },
                    Handler(Looper.getMainLooper()),
                )
            } catch (e: IllegalArgumentException) {
                callback.invoke(null)
            }
        }
    }

    private fun screenShot(bitmap: Bitmap?) {
        try {
            val cachePath = File(this.requireContext().cacheDir, "images")
            cachePath.mkdirs()

            val stream = FileOutputStream("$cachePath/image.png")
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val newFile = File(cachePath, "image.png")

            val contentUri: Uri = FileProvider.getUriForFile(
                this.requireContext(),
                "sopt.uni.fileprovider",
                newFile,
            )

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, contentUri)
                type = "image/png"
            }
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_wishcoupon)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun shareWishCoupon() {
        binding.llShareWishCoupon.setOnClickListener {
            getBitmapFromWishCoupon(binding.clWishUseMy) { bitmap ->
                screenShot(bitmap)
            }
        }
    }
}
