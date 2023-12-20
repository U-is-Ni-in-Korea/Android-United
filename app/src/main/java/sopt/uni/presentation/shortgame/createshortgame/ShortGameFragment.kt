package sopt.uni.presentation.shortgame.createshortgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.BindingAdapter
import androidx.fragment.app.activityViewModels
import sopt.uni.R
import sopt.uni.databinding.FragmentShortGameBinding
import sopt.uni.presentation.shortgame.createshortgame.dialog.CreateShortGameDialogFragment
import sopt.uni.util.ItemDecorations
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showToast

class ShortGameFragment : BindingFragment<FragmentShortGameBinding>(R.layout.fragment_short_game) {
    private val viewModel by activityViewModels<CreateShortGameViewModel>()

    private val missionAdapter by lazy {
        MissionCategoryAdapter(
            selectMissionClickListener = { missionId ->
                goToMissionDetail(missionId)
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.apply {
            viewModel = this@ShortGameFragment.viewModel
        }
        setBackPressed()
        setAdapter()
        setClickListener()
        setViewModelObserve()

        return binding.root
    }

    private fun setBackPressed() {
        this.activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    exitDialog()
                }
            },
        )
    }

    private fun setAdapter() {
        binding.rvMission.adapter = missionAdapter
        binding.rvMission.addItemDecoration(ItemDecorations(0, 9, 0, 9))
    }

    private fun setViewModelObserve() {
        viewModel.missionList.observe(viewLifecycleOwner) {
            missionAdapter.submitList(it)
        }
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnSingleClickListener {
                exitDialog()
            }
        }
    }

    private fun goToMissionDetail(missionId: Int) {
        if ((viewModel.contentLength.value ?: 0) > MAX_LENGTH) {
            requireActivity().showToast(getString(R.string.charactor_over_error_message))
            return
        }
        viewModel.setSelectedMissionId(missionId)
        val activity = activity as CreateShortGameActivity
        activity.changeFragment(getString(R.string.label_mission_detail))
    }

    private fun exitDialog() {
        requireActivity().let {
            CreateShortGameDialogFragment().apply {
                titleText =
                    it.resources.getString(R.string.create_short_game_exit_dialog_title)
                bodyText =
                    it.resources.getString(R.string.create_short_game_exit_dialog_body)
                confirmButtonText =
                    it.resources.getString(R.string.create_short_game_exit_dialog_exit)
                dismissButtonText =
                    it.resources.getString(R.string.dialog_cancel_text)
                confirmClickListener = {
                    it.finish()
                    this.dismiss()
                }
                dismissClickListener = {
                    this.dismiss()
                }
            }.show(it.supportFragmentManager, "")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ShortGameFragment {
            val args = Bundle()
            val fragment = ShortGameFragment()
            fragment.arguments = args
            return fragment
        }

        private const val MAX_LENGTH = 54

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setContentLength(view: TextView, length: Int) {
            if (length > MAX_LENGTH) {
                view.setTextColor(view.context.getColor(R.color.Red_500))
            } else {
                view.setTextColor(view.context.getColor(R.color.Gray_200))
            }
            view.text = "$length/$MAX_LENGTH"
        }

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setWishContent(view: EditText, length: Int) {
            if (length > MAX_LENGTH && view.isFocused) {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text_error)
            } else {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text)
            }
        }
    }
}
