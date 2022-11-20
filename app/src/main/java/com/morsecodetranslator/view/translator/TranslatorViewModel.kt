package com.morsecodetranslator.view.translator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.data.MorseModel
import com.morsecodetranslator.data.getMorseDatabase
import kotlinx.coroutines.launch

class TranslatorViewModel : ViewModel() {

    val getTextToMorseTranslateState = MutableLiveData<ViewState<String>>()
    val getMorseToTextTranslateState = MutableLiveData<ViewState<String>>()

    fun morseCodeIntoTextTranslate(message: String) {

        viewModelScope.launch {

            try {
                var translated = ""

                val words = message.split(" ")
                words.forEach {
                    val morse = findMorseByCode(it)
                    val key = morse?.key ?: '?'
                    translated += key
                }

                getMorseToTextTranslateState.postValue(ViewState.Success(translated))
            } catch (error: Exception) {
                getMorseToTextTranslateState.postValue(ViewState.Error("Gagal"))
            }

        }

    }

    fun textIntoMorseCodeTranslate(message: String) {

        viewModelScope.launch {

            try {

                var translated = ""

                val messageArray = message.toCharArray()
                messageArray.forEach {
                    val morse = findMorseByKey(it)
                    val code = morse?.code ?: '?'
                    translated += if (translated.isEmpty()) code else " $code"
                }

                getTextToMorseTranslateState.postValue(ViewState.Success(translated))

            } catch (error: Exception) {
                getTextToMorseTranslateState.postValue(ViewState.Error("Gagal"))
            }

        }

    }

    private fun findMorseByKey(key: Char): MorseModel? {
        return try {
            val morse = getMorseDatabase().find {
                key.uppercaseChar() == it.key
            }
            return morse
        } catch (error: Exception) {
            null
        }
    }

    private fun findMorseByCode(code: String): MorseModel? {

        return try {
            val morse = getMorseDatabase().find {
                code.uppercase() == it.code
            }
            return morse
        } catch (error: Exception) {
            null
        }
    }

}