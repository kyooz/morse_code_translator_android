package com.morsecodetranslator.view.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.morsecodetranslator.common.startActivityLeftTransition
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivitySplashBinding
import com.morsecodetranslator.view.translator.TranslatorActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

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