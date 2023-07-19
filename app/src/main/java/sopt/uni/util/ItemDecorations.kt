package sopt.uni.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class ItemDecorations(
    private val topSpace: Int,
    private val bottomSpace: Int,
    private val leftSpace: Int,
    private val rightSpace: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = dpToPx(topSpace)
        outRect.bottom = dpToPx(bottomSpace)
        outRect.left = dpToPx(leftSpace)
        outRect.right = dpToPx(rightSpace)
    }

    private fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }
}
