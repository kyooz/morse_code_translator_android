package com.morsecodetranslator.view.comunication

import android.os.Bundle
import android.os.PersistableBundle
import com.morsecodetranslator.R
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityFlashComunicationBinding
import com.morsecodetranslator.view.base.BaseActivity

class FlashComunicationActivity: BaseActivity() {

    private val binding by viewBinding(ActivityFlashComunicationBinding::inflate)

    companion object {
        val MESSAGE_ARG = "MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)

        initView()
        val message = intent.getStringExtra(MESSAGE_ARG)

    }

    private fun initView() {

//        binding.toolbar.apply {
//            title = "Flash Communication"
//            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_white_24)
//
//        }

    }


}