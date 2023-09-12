//package sopt.uni.presentation.timer
//
//import android.content.Context
//import android.util.Log
//import java.util.Timer
//
//class TimerWorker(private val context: Context, private val uiUpdateCallback: (Long) -> Unit) {
//
//    private var timerTask: Timer? = null
//    private fun runTimer(totalTime: Long) {
//        var remainingTime = totalTime // var로 변경
//        val sharedPreferences =
//            context.getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
//
//        if (remainingTime >= 0) {
//            timerTask = kotlin.concurrent.timer(period = 1000) {    // timer() 호출
//                remainingTime--
//                Log.d("Timer", "Remaining Seconds: $totalTime")
//
//                sharedPreferences.edit().putLong("remainingSeconds", remainingTime).apply()
//                // UI조작을 위한 메서드
//                uiUpdateCallback(remainingTime)
//            }
//        }
//
//    }
//}
