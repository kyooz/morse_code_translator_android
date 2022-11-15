package com.morsecodetranslator.view.translator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.morsecodetranslator.R
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.common.intentFor
import com.morsecodetranslator.databinding.FragmentTextToMorseBinding
import com.morsecodetranslator.view.base.BaseFragment
import com.morsecodetranslator.view.comunication.FlashComunicationActivity

class TextToMorseFragment :
    BaseFragment<FragmentTextToMorseBinding>(FragmentTextToMorseBinding::inflate) {

    private lateinit var viewModel: TranslatorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set view model
        viewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)

        setView()
        startObservingData()

    }

    private fun setView() {

        binding.btnTranslate.setOnClickListener {

            val message = binding.edText.text.toString()

            if (message.isEmpty()) {
                toast(getString(R.string.please_input_message))
            } else {
                viewModel.textToMorseTranslate(message)
            }

        }

    }

    private fun startObservingData() {

        viewModel.getTextToMorseTranslateState.observe(requireActivity()) {
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

    private fun showMessageDialog(message: String) {

        val dialog = TranslatedBottomDialog(
            message,
            true
        )
        dialog.show(childFragmentManager, "dialog")

        dialog.setOnFlashMessage(object : TranslatedBottomDialog.OnSelectedItemDialog {
            override fun onFlashMessage(message: String) {
                navigateToFlashCommunication(message)
            }
        })

    }

    private fun navigateToFlashCommunication(message: String) {
        setLog("message : $message")
//        val intent = Intent(requireContext(), FlashComunicationActivity::class.java).apply {
//            putExtra(FlashComunicationActivity.MESSAGE_ARG, message)
//        }

        val bundle = Bundle()
        bundle.putString(FlashComunicationActivity.MESSAGE_ARG, message)

        val intent = Intent(requireContext(), FlashComunicationActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun setLog(msg: String) {
        Log.e("morse", msg)
    }


}