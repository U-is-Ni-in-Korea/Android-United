package sopt.uni.presentation.timer

import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private var totalSeconds: Long = 0L

    override fun doWork(): Result {

        val inputData = inputData
        totalSeconds = inputData.getLong("totalSeconds", 0L)

        // UI 스레드에서 CountDownTimer 실행
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val timer = object : CountDownTimer(totalSeconds * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // 타이머가 실행 중일 때 매 초마다 호출되는 부분입니다.
                    // 남은 시간을 계산하거나 업데이트할 수 있습니다.
                    val remainingSeconds = millisUntilFinished / 1000
                    Log.d("TimerWorker", "Remaining Seconds: $remainingSeconds")

                    // 남은 시간을 저장하거나 업데이트하는 등의 작업을 수행할 수 있습니다.
                    // 백그라운드 스레드에서 SharedPreferences를 사용하여 남은 시간을 저장
                    val sharedPreferences =
                        applicationContext.getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putLong("remainingSeconds", remainingSeconds).apply()
                    sharedPreferences.edit().putBoolean("isTimerActive", true).apply()
                }

                override fun onFinish() {
                    val sharedPreferences =
                        applicationContext.getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().remove("remainingSeconds").apply()
                    sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
                }
            }
            timer.start()
        }

        // Worker가 실행을 완료했을 때 Result.success()를 반환합니다.
        return Result.success()
    }
}
