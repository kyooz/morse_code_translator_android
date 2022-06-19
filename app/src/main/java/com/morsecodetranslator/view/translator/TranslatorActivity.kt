package com.morsecodetranslator.view.translator

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.morsecodetranslator.R
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityTranslatorBinding
import com.morsecodetranslator.view.base.BaseActivity


class TranslatorActivity : BaseActivity() {

    private val binding by viewBinding(ActivityTranslatorBinding::inflate)
    private lateinit var viewModel: TranslatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // set view model
        viewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)

        setView()

    }

    private fun setView() {

        val adapter = ViewPagerAdapter()

        val tabLayoutMediator = TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            val badgeDrawable = tab.orCreateBadge
            when (position) {
                0 -> {
                    tab.text = "Text To Morse"
                    badgeDrawable.isVisible = false
                    badgeDrawable.number = 19
                    badgeDrawable.backgroundColor = ContextCompat.getColor(this, R.color.colorPrimary)
                }
                1 -> {
                    tab.text = "Morse To Text"
                    badgeDrawable.isVisible = false
                    badgeDrawable.number = 19
                    badgeDrawable.backgroundColor =  ContextCompat.getColor(this, R.color.colorPrimary)
                }
            }
        }

        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = adapter
        tabLayoutMediator.attach()
    }

    inner class ViewPagerAdapter : FragmentStateAdapter(this) {

        private val listFragment = listOf(
            TextToMorseFragment(),
            MorseToTextFragment(),
        )

        override fun getItemCount() = listFragment.size

        override fun createFragment(position: Int): Fragment {
            return listFragment[position]
        }
    }

}