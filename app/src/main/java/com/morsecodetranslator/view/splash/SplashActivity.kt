package com.morsecodetranslator.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.morsecodetranslator.R
import com.morsecodetranslator.common.startActivityLeftTransition
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivitySplashBinding
import com.morsecodetranslator.view.base.BaseActivity
import com.morsecodetranslator.view.translator.TranslatorActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navigateToTranslator()

    }

    private fun navigateToTranslator() {

        GlobalScope.launch {
            delay(3000) // 3 second
            withContext(Dispatchers.IO) {
                startActivityLeftTransition<TranslatorActivity>()
                finish()
            }
        }

    }

}