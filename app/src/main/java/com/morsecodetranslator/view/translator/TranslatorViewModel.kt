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

    fun morseToTextTranslate(morseMessage: String) {

        viewModelScope.launch {

            try {
                var translated = ""

                val words = morseMessage.split(" ")
                words.forEach {
                    val morse = findMorseByCode(it)
                    val code = morse?.code ?: '?'
                    translated += code
                }

                getMorseToTextTranslateState.postValue(ViewState.Success(translated))
            } catch (error: Exception) {
                getMorseToTextTranslateState.postValue(ViewState.Error("Gagal"))
            }

        }

    }

    fun textToMorseTranslate(textMessage: String) {

        viewModelScope.launch {

            try {

                var translated = ""

                val messageArray = textMessage.toCharArray()
                messageArray.forEach {
                    val morse = findMorseByKey(it)
                    val key = morse?.key ?: '?'
                    translated += if (translated.isEmpty()) key else " $key"
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