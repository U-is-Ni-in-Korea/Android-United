package sopt.uni.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.uni.data.entity.onboarding.OnBoardingItem
import sopt.uni.databinding.ItemOnboardingBinding
import sopt.uni.util.extension.ItemDiffCallback

class OnBoardingAdaptor : ListAdapter<OnBoardingItem, OnBoardingViewHolder>(
    ItemDiffCallback<OnBoardingItem>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding =
            ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
