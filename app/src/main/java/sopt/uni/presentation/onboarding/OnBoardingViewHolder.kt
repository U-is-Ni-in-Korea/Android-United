package sopt.uni.presentation.onboarding

import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.uni.data.entity.onboarding.OnBoardingItem
import sopt.uni.databinding.ItemOnboardingBinding

class OnBoardingViewHolder(val binding: ItemOnboardingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: OnBoardingItem) {
        binding.ivOnBoardingImage.load(data.image)
        binding.tvOnBoardingTitle.text = data.title
        binding.tvOnBoardingDescription.text = data.description
    }
}
