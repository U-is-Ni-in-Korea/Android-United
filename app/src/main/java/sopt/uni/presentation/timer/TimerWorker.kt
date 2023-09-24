package sopt.uni.presentation.timer

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private var totalSeconds: Long = ZEROLONG

    override fun doWork(): Result {
        val sharedPreferences =
            applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val inputData = inputData
        totalSeconds = inputData.getLong(TOTALTIMEKEY, ZEROLONG)
        val startTimer = sharedPreferences.getBoolean(ACTIVEKEY, false)

        while (totalSeconds >= ZERO && startTimer) {
            val cancel = sharedPreferences.getBoolean(ACTIVEKEY, true)
            val pause = sharedPreferences.getBoolean(PAUSEKEY, false)

            if (!cancel) {
                break
            }
            if (pause) {
                continue
            }
            totalSeconds--
            sharedPreferences.edit().putLong(REMAINTIMEKEY, totalSeconds).apply()
            Thread.sleep(ONESECONDS)
        }

        sharedPreferences.edit().putBoolean(ACTIVEKEY, false).apply()
        sharedPreferences.edit().remove(REMAINTIMEKEY).apply()
        sharedPreferences.edit().remove(TOTALTIMEKEY).apply()

        return Result.success()
    }

    companion object {
        private const val NAME = "timer_prefs"
        private const val PAUSEKEY = "isTimerPause"
        private const val ACTIVEKEY = "isTimerActive"
        private const val TOTALTIMEKEY = "totalTime"
        private const val REMAINTIMEKEY = "remainingSeconds"
        private const val ONESECONDS = 1000L
        private const val ZEROLONG = 0L
        private const val ZERO = 0
    }
}
