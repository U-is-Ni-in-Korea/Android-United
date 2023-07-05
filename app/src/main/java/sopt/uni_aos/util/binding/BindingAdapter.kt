package sopt.uni_aos.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, imgUrl: String?) {
        view.load(imgUrl)
    }
}
