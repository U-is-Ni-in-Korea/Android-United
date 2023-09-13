package sopt.uni.presentation.timer

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private var totalSeconds: Long = 0L

    override fun doWork(): Result {
        val sharedPreferences =
            applicationContext.getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val inputData = inputData
        totalSeconds = inputData.getLong("totalSeconds", 0L)
        val startTimer = sharedPreferences.getBoolean("isTimerActive", false)

        while (totalSeconds >= 0 && startTimer) {
            val cancel = sharedPreferences.getBoolean("isTimerActive", true)
            val pause = sharedPreferences.getBoolean("isTimerPause", false)

            if (!cancel) {
                break
            }

            if (pause) {
                continue
            }

            Log.d("TimerWorker", "Remaining Seconds: $totalSeconds")
            Thread.sleep(1000)
            totalSeconds--
            sharedPreferences.edit().putLong("remainingSeconds", totalSeconds).apply()
        }

        sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
        sharedPreferences.edit().remove("remainingSeconds").apply()
        sharedPreferences.edit().remove("total_time").apply()

        // Worker가 실행을 완료했을 때 Result.success()를 반환합니다.
        return Result.success()
    }

}
