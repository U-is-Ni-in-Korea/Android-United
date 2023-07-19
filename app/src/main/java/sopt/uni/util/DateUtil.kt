package sopt.uni.util

object DateUtil {
    fun formatedMonth(month: Int) =
        if (month <= 0) {
            throw IllegalArgumentException("month can not be less than equal 0")
        } else if (month < 10) {
            "0$month"
        } else {
            "$month"
        }

    fun formatedDay(day: Int) =
        if (day <= 0 || day > 31) {
            throw IllegalArgumentException("day can not be less than equal 0 or greater than 31")
        } else if (day < 10) {
            "0$day"
        } else {
            "$day"
        }
}
