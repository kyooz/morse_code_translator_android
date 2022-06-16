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
                words.forEach {
                    val data = translatorData.checkMorseDataByCode(it)
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
                    val data = translatorData.checkMorseDataByChar(it)
                    translated += if (translated.isEmpty()) data else " $data"
                }

                getTextToMorseTranslateState.postValue(ViewState.Success(translated))

            } catch (error: Exception) {
                getTextToMorseTranslateState.postValue(ViewState.Error("Gagal"))
            }

        }

    }

}