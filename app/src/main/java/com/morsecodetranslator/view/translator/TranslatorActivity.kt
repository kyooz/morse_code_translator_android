package com.morsecodetranslator.view.translator

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
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

//        viewModel.textToMorseTranslate("SOS")
//        viewModel.morseToTextTranslate("... --- ...")
//        viewModel.morseToTextTranslate("... --- ... / ... --- ...")

        startObservingData()

    }

    private fun startObservingData() {

        viewModel.getMorseToTextTranslateState.observe(this) {
            when (it) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    setLog("morseToTextTranslateState Success: ${it.data}")
                }
                is ViewState.Error -> {
                    toast(it.viewError ?: "Gagal")
                }
            }
        }

        viewModel.getTextToMorseTranslateState.observe(this) {
            when (it) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    setLog("textToMorseTranslateState Success: ${it.data}")
                }
                is ViewState.Error -> {
                    toast(it.viewError ?: "Gagal")
                }
            }
        }

    }

    private fun setLog(msg: String) {
        Log.e("Translator", msg)
    }


}