package com.morsecodetranslator.view.comunication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.data.SPACE
import com.morsecodetranslator.data.STRIP
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FlashCommunicationViewModel : ViewModel() {

    val flashState = MutableLiveData<ViewState<Boolean>>()

    private val DOT_FLASH_DURATION: Long = 500
    private val STRIP_FLASH_DURATION: Long = 1000

    private val SPACE_FLASH_OFF_DURATION: Long = 2000
    private val DEFAULT_FLASH_OFF_DURATION: Long = 500

    fun startFlash(morse: String) {
        viewModelScope.launch {
            try {

                // remove whitespace
                val message = morse.replace("\\s".toRegex(), "")
                val morseArray = message.toCharArray()

                setLog("morseArray size : ${morseArray.size}")

                morseArray.forEach {

                    setLog("code : $it")
                    if (it != SPACE) {
                        setLog("flash on ${durationFlashOn(it)}")
                        flashState.postValue(ViewState.Success(true))
                        delay(durationFlashOn(it))
                    }
                    setLog("flash off")
                    flashState.postValue(ViewState.Success(false))
                    delay(durationFlashOff(it))
                }

            } catch (error: Exception) {
                setLog("error : ${error.message}")
                flashState.postValue(ViewState.Error(error.message))
            }
        }
    }

    private fun durationFlashOn(char: Char): Long {
        return when (char) {
            STRIP -> STRIP_FLASH_DURATION
            else -> DOT_FLASH_DURATION // DOT
        }
    }

    private fun durationFlashOff(char: Char): Long {
        return when (char) {
            SPACE -> SPACE_FLASH_OFF_DURATION
            else -> DEFAULT_FLASH_OFF_DURATION
        }
    }

    private fun setLog(msg: String) {
        Log.e("flash", msg)
    }

}