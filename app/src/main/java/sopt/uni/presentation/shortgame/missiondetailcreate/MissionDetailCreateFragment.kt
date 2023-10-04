package sopt.uni.presentation.shortgame.missiondetailcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import sopt.uni.R
import sopt.uni.databinding.FragmentMissionDetailCreateBinding
import sopt.uni.presentation.shortgame.createshortgame.CreateShortGameActivity
import sopt.uni.presentation.shortgame.createshortgame.CreateShortGameViewModel
import sopt.uni.presentation.shortgame.createshortgame.dialog.CreateShortGameDialogFragment
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener

class MissionDetailCreateFragment :
    BindingFragment<FragmentMissionDetailCreateBinding>(R.layout.fragment_mission_detail_create) {
    private val viewModel by activityViewModels<CreateShortGameViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.apply {
            viewModel = this@MissionDetailCreateFragment.viewModel
        }
        setViewModelObserve()
        setClickListener()
        setBackPressed()
        return binding.root
    }

    private fun setViewModelObserve() {
        viewModel.isCreateSuccess.observe(viewLifecycleOwner) {
            if (it) {
                MissionRecordActivity.start(
                    this.requireContext(),
                    viewModel.roundGameId.value
                        ?: return@observe,
                )
                requireActivity().finish()
            }
        }
    }

    private fun setBackPressed() {
        this.activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goToShortGame()
                }
            },
        )
    }

    private fun setClickListener() {
        binding.apply {
            missionDetailCreateBack.setOnSingleClickListener {
                goToShortGame()
            }
            btnCreateShortGame.setOnSingleClickListener {
                createDialog()
            }
        }
    }

    private fun goToShortGame() {
        val activity = activity as CreateShortGameActivity
        activity.changeFragment(getString(R.string.label_short_game))
    }

    private fun createDialog() {
        requireActivity().let {
            CreateShortGameDialogFragment().apply {
                titleText =
                    it.resources.getString(R.string.create_short_game_create_dialog_title)
                bodyText =
                    it.resources.getString(R.string.create_short_game_create_dialog_body)
                confirmButtonText =
                    it.resources.getString(R.string.create_short_game_create_dialog_create)
                dismissButtonText =
                    it.resources.getString(R.string.dialog_cancel_text)
                confirmClickListener = {
                    viewModel.createShortGame()
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
        fun newInstance(): MissionDetailCreateFragment {
            val args = Bundle()
            val fragment = MissionDetailCreateFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
