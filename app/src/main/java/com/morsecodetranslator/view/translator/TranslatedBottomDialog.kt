package com.morsecodetranslator.view.translator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.morsecodetranslator.R
import com.morsecodetranslator.common.copyToClipboard
import com.morsecodetranslator.data.KurirInterface
import com.morsecodetranslator.databinding.DialogBottomTranslatedBinding
import com.morsecodetranslator.view.base.BaseFragmentBottomDialog

class TranslatedBottomDialog(val message: String, val isTextToMorse: Boolean) :
    BaseFragmentBottomDialog<DialogBottomTranslatedBinding>(DialogBottomTranslatedBinding::inflate) {

    lateinit var kurirInterface: KurirInterface


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
            kurirInterface.onMenyimpanPesan(message)
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


    fun getMenerimaPesan(kurir : KurirInterface){
        this.kurirInterface = kurir
    }

}

// 1.membuat interface (onMenyimpanPesan)
// 2.lateinit var kurirInterface: KurirInterface
// 3.kurirInterface.onMenyimpanPesan(message)
// 4.fun getMenerimaPesan