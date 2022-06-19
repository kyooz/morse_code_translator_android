package com.morsecodetranslator.view.translator

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.morsecodetranslator.R
import com.morsecodetranslator.common.copyToClipboard
import com.morsecodetranslator.databinding.DialogBottomTranslatedBinding
import com.morsecodetranslator.view.base.BaseFragmentBottomDialog

class TranslatedBottomDialog(val message: String, val isTextToMorse: Boolean)
    : BaseFragmentBottomDialog<DialogBottomTranslatedBinding>(DialogBottomTranslatedBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionView()

        binding.tvMessage.text = message
        binding.tvTitle.text =
            if (isTextToMorse) "Text to Morse translated"
            else "Morse to Text translated"

        binding.btnFlashOn.isVisible = isTextToMorse

    }

    private fun setActionView() {

        binding.btnCopy.setOnClickListener {
            requireContext().copyToClipboard("Copy label", message)
        }

        binding.btnShare.setOnClickListener {
            toast(getString(R.string.under_constructor))
        }

        binding.imgClose.setOnClickListener {
            dismiss()
        }

        binding.btnFlashOn.setOnClickListener {
            toast(getString(R.string.under_constructor))
            dismiss()
        }

    }

}