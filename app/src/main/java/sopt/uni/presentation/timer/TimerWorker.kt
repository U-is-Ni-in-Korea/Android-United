package sopt.uni.presentation.timer

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber

class TimerWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private var totalSeconds: Long = 0L

    override fun doWork(): Result {
        val sharedPreferences =
            applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val inputData = inputData
        totalSeconds = inputData.getLong(TOTALTIMEKEY, 0L)
        val startTimer = sharedPreferences.getBoolean(ACTIVEKEY, false)

        while (totalSeconds >= 0 && startTimer) {
            val cancel = sharedPreferences.getBoolean(ACTIVEKEY, true)
            val pause = sharedPreferences.getBoolean(PAUSEKEY, false)

            if (!cancel) {
                break
            }
            if (pause) {
                continue
            }
            Timber.d("Remaining Seconds: $totalSeconds")
            totalSeconds--
            sharedPreferences.edit().putLong(REMAINTIMEKEY, totalSeconds).apply()
            Thread.sleep(1000)
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
    }
}
