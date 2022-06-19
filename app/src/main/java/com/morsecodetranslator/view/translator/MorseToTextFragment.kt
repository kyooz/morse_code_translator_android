package com.morsecodetranslator.view.translator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.morsecodetranslator.R
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.common.hideKeyboard
import com.morsecodetranslator.databinding.FragmentMorseToTextBinding
import com.morsecodetranslator.view.base.BaseFragment

class MorseToTextFragment :
    BaseFragment<FragmentMorseToTextBinding>(FragmentMorseToTextBinding::inflate) {

    private lateinit var viewModel: TranslatorViewModel

    private val STRIP = "-"
    private val DOT = "."
    private val DELETED = "deleted"
    private val LETTER_SPACE = "letter"
    private val ENTER_SPACE = "enter"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set view model
        viewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)

        setView()
        startObservingData()

    }

    private fun setView() {

        binding.cardDot.setOnClickListener {
            setOnClickButtonTyping(DOT)
        }

        binding.cardStrip.setOnClickListener {
            setOnClickButtonTyping(STRIP)
        }

        binding.cardDelete.setOnClickListener {
            val text = binding.tvMorse.text.toString()
            if (text.isNotEmpty()) {
                setOnClickButtonTyping(DELETED)
            }
        }

        binding.btnEnterSpace.setOnClickListener {
            setOnClickButtonTyping(ENTER_SPACE)
        }

        binding.btnLetterSpace.setOnClickListener {
            setOnClickButtonTyping(LETTER_SPACE)
        }

        binding.btnTranslate.setOnClickListener {

            val message = binding.tvMorse.text.toString()

            if (message.isEmpty()) {
                toast(getString(R.string.please_input_message))
            } else {
                val data = message.dropLast(1)
                setLog("message : $data")
                viewModel.morseToTextTranslate(data)
            }

        }

    }

    private fun startObservingData() {

        viewModel.getMorseToTextTranslateState.observe(requireActivity()) {
            when (it) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    showMessageDialog(it.data)
                }
                is ViewState.Error -> {
                    toast(it.viewError ?: "Gagal")
                }
            }
        }

    }

    private fun setOnClickButtonTyping(typing: String) {

        val text = binding.tvMorse.text.toString()
        var morse = if (text.isEmpty()) "" else text.dropLast(1)

        val message = when (typing) {
            DELETED -> morse.dropLast(1)
            LETTER_SPACE -> "$morse "
            ENTER_SPACE -> "$morse / "
            else -> morse + typing
        }

        binding.tvMorse.text = "$message|"

    }

    private fun showMessageDialog(message: String) {

        val dialog = TranslatedBottomDialog(
            message,
            false
        )
        dialog.show(childFragmentManager, "dialog")

    }

    private fun setLog(msg: String) {
        Log.e("MorseToText", msg)
    }

}