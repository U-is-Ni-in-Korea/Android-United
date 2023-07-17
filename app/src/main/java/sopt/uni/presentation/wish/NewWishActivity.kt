package sopt.uni.presentation.wish

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentWishNewWishBinding
import sopt.uni.util.binding.BindingActivity

@AndroidEntryPoint
class NewWishActivity :
    BindingActivity<FragmentWishNewWishBinding>(R.layout.fragment_wish_new_wish) {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBlurEffect()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setBlurEffect() {
        binding.cvNewWish.setRenderEffect(
            RenderEffect.createBlurEffect(
                10f,
                10f,
                Shader.TileMode.REPEAT,
            ),
        )
    }
}
