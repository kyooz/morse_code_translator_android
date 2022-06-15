package com.morsecodetranslator.view.translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityTranslatorBinding
import com.morsecodetranslator.view.base.BaseActivity

class TranslatorActivity : BaseActivity() {

    private val binding by viewBinding(ActivityTranslatorBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}