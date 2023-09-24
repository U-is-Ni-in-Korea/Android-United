package sopt.uni.presentation.timer

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import sopt.uni.data.datasource.local.SparkleStorage

class TimerWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private var totalSeconds: Long = ZEROLONG

    override fun doWork(): Result {
        val inputData = inputData
        totalSeconds = inputData.getLong(TOTALTIMEKEY, ZEROLONG)
        val startTimer = SparkleStorage.isActive

        while (totalSeconds >= ZERO && startTimer) {
            val cancel = SparkleStorage.isActive
            val pause = SparkleStorage.isPause

            if (!cancel) {
                break
            }
            if (pause) {
                continue
            }
            totalSeconds--
            SparkleStorage.remainTime = totalSeconds
            Thread.sleep(ONESECONDS)
        }

        SparkleStorage.timerClear()

        return Result.success()
    }

    companion object {
        private const val TOTALTIMEKEY = "totalTime"
        private const val ONESECONDS = 1000L
        private const val ZEROLONG = 0L
        private const val ZERO = 0
    }
}
