package sopt.uni.presentation.timer

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.FragmentTimerSettingBinding
import sopt.uni.util.MakeVibrator
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar

@AndroidEntryPoint
class TimerSettingFragment :
    BindingFragment<FragmentTimerSettingBinding>(R.layout.fragment_timer_setting) {
    private val viewModel by activityViewModels<TimerViewModel>()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var audioManager: AudioManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTimerSetting()
        setupStartButton()
        timerFinished()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun timerFinished() {
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { message ->
            if (!message.isNullOrEmpty()) {
                showSnackbar(binding.root, message)
                viewModel.resetSnackbarMessage()
                vibrateOrPlaySound()
            }
        }
    }

    private fun vibrateOrPlaySound() {
        audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val ringerMode = audioManager.ringerMode
        if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
            vibrateSingle()
        } else if (ringerMode == AudioManager.RINGER_MODE_NORMAL) {
            playSound()
        }
    }

    private fun playSound() {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.chime_time)
                mediaPlayer?.start()
                object : CountDownTimer(TENSECONDS, ONESECONDS) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun vibrateSingle() {
        val vibrator = MakeVibrator()
        vibrator.init(requireContext())
        vibrator.make(TWOSECONDS)
    }

    private fun setupStartButton() {
        binding.btnTimerStart.setOnSingleClickListener {
            val minuteValue = binding.numberpickerMinute.value
            val secondValue = binding.numberpickerSeconds.value
            val total = minuteValue * SIXTY + secondValue

            initsharedPrefSetting(total)
            setWorkManager(total)
            goTimerActiveFragment(total)
        }
    }

    private fun goTimerActiveFragment(total: Int) {
        if (total == ZERO) {
            showSnackbar(binding.root, getString(R.string.timer_snackbar_message))
        } else {
            val fragmentTimerActive = TimerActiveFragment(total.toFloat())
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
            fragmentTransaction.commit()
        }
    }

    private fun setWorkManager(total: Int) {
        val data = Data.Builder()
            .putLong(TOTALTIMEKEY, total.toLong())
            .build()

        val timerWorkRequest = OneTimeWorkRequestBuilder<TimerWorker>()
            .setInputData(data)
            .build()

        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(timerWorkRequest)
    }

    private fun initsharedPrefSetting(total: Int) {
        SparkleStorage.totalTime = total.toFloat()
        SparkleStorage.isActive = true
        SparkleStorage.isPause = false
    }

    private fun initTimerSetting() {
        binding.numberpickerMinute.apply {
            minValue = ZERO
            maxValue = MAXNUM
            setFormatter { i ->
                String.format(context.getString(R.string.timer_number_picker_format), i)
            }
        }

        binding.numberpickerSeconds.apply {
            minValue = ZERO
            maxValue = MAXNUM
            setFormatter { i ->
                String.format(context.getString(R.string.timer_number_picker_format), i)
            }
        }
    }

    companion object {
        private const val TOTALTIMEKEY = "totalTime"
        private const val ZERO = 0
        private const val MAXNUM = 59
        private const val SIXTY = 60
        private const val ONESECONDS = 1000L
        private const val TWOSECONDS = 2000L
        private const val TENSECONDS = 10000L
    }
}
