package com.morsecodetranslator.view.translator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.morsecodetranslator.R
import com.morsecodetranslator.common.copyToClipboard
import com.morsecodetranslator.databinding.DialogBottomTranslatedBinding
import com.morsecodetranslator.view.base.BaseFragmentBottomDialog

class TranslatedBottomDialog(val message: String, val isTextToMorse: Boolean) :
    BaseFragmentBottomDialog<DialogBottomTranslatedBinding>(DialogBottomTranslatedBinding::inflate) {

    lateinit var onClickItem: OnSelectedItemDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionView()

        binding.tvMessage.text = message
        binding.tvTitle.text =
            if (isTextToMorse) getString(R.string.text_to_morse_translated)
            else getString(R.string.morse_to_text_translated)

    }

    private fun setActionView() {

        binding.ivCopy.setOnClickListener {
            requireContext().copyToClipboard(getString(R.string.core_copied), message)
        }

        binding.ivShare.setOnClickListener {
            shareTranslator(message)
        }

        binding.imgClose.setOnClickListener {
            dismiss()
        }

        binding.ivFlash.setOnClickListener {
            onClickItem.onFlashMessage(message)
            dismiss()
        }

    }

    private fun shareTranslator(message: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


    interface OnSelectedItemDialog {
        fun onFlashMessage(message: String)
    }

    private fun onClick(message: String) {
        onClickItem.onFlashMessage(message)
    }

    fun setOnFlashMessage(onClickItem: OnSelectedItemDialog) {
        this.onClickItem = onClickItem
    }

}