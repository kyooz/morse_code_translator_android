package com.morsecodetranslator.view.translator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.data.TranslatorData
import kotlinx.coroutines.launch
import java.util.*

class TranslatorViewModel : ViewModel() {

    val getTextToMorseTranslateState = MutableLiveData<ViewState<String>>()
    val getMorseToTextTranslateState = MutableLiveData<ViewState<String>>()

    private val translatorData = TranslatorData()

    fun morseToTextTranslate(morseMessage: String) {

        viewModelScope.launch {

            try {
                var translated = ""

                val words = morseMessage.split(" ")
                setLog("words array : $words")
                words.forEach {
                    setLog("input : $it")
                    val data = translatorData.checkMorseDataByCode(it)
                    setLog("translated : $data")
                    translated += data
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
                    val data = translatorData.checkMorseDataByChar(it.uppercaseChar())
                    translated += if (translated.isEmpty()) data else " $data"
                }

                getTextToMorseTranslateState.postValue(ViewState.Success(translated))

            } catch (error: Exception) {
                getTextToMorseTranslateState.postValue(ViewState.Error("Gagal"))
            }

        }

    }

    private fun setLog(msg: String) {
        Log.e("Translator", msg)
    }

}