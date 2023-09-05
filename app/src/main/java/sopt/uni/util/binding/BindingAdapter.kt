package sopt.uni.util.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import sopt.uni.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, imgUrl: String?) {
        view.load(imgUrl)
    }

    @JvmStatic
    @BindingAdapter("result", "time")
    fun TextView.setResultTextField(result: String, time: String) {
        if (result == "WIN") {
            setBackgroundResource(R.drawable.bg_mission_success_textbox)
            setTextColor(ContextCompat.getColor(context, R.color.Green_600))
            text = String.format(resources.getString(R.string.history_detail_mission_success), time)
        } else {
            setBackgroundResource(R.drawable.bg_mission_fail_textbox)
            setTextColor(ContextCompat.getColor(context, R.color.Pink_600))
            text = resources.getString(R.string.history_detail_mission_fail_text)
        }
    }

    @JvmStatic
    @BindingAdapter("startDate")
    fun TextView.setCoupleDate(startDate: String) {
        val parsedDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE)
        text = parsedDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.getDefault()))
    }

    @JvmStatic
    @BindingAdapter("convertToMinAndSec")
    fun TextView.convertToMinAndSec(left: Long) {
        val minutes = left / 60
        val seconds = left % 60
        text = String.format(context.getString(R.string.timer_left_time_format), minutes, seconds)
    }
}
