package com.morsecodetranslator.view.comunication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.morsecodetranslator.R
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityFlashComunicationBinding
import com.morsecodetranslator.view.base.BaseActivity
import kotlinx.coroutines.*

class FlashComunicationActivity : BaseActivity() {

    private val binding by viewBinding(ActivityFlashComunicationBinding::inflate)

    // flash permission
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    companion object {
        val MESSAGE_ARG = "MESSAGE"

        private val DOT = '.'
        private val STRIP = '-'
        private val SPACE = '/'

        private val DOT_DURATION: Long = 500
        private val STRIP_DURATION: Long = 1000

        private val SPACE_DURATION: Long = 2000
        private val DEFAULT_DURATION: Long = 500
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)

        setLog("isFlashAvailable : ${isFlashAvailable()}")

        initView()
        setUpFlash()
        getIntentData()

    }

    private fun initView() {

//        binding.toolbar.apply {
//            title = "Flash Communication"
//            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_white_24)
//
//        }

    }

    private fun getIntentData() {

        val message = intent.extras?.getString(MESSAGE_ARG)

        setLog("message result : $message")
        setLog("isFlashAvailable : ${isFlashAvailable()}")

        if (isFlashAvailable()) {
            startFlashMessage(message ?: "")
        }

    }

    private fun setUpFlash() {

        if (isFlashAvailable()) {
            cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            try {
                cameraId = cameraManager.cameraIdList[0]
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        } else {
            showNoFlashNotAvailable()
        }

    }

    private fun isFlashAvailable(): Boolean {
        return applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
    }

    private fun startFlashMessage(message: String) {

        val messageArray = message.toCharArray()

        messageArray.forEach { char ->

            GlobalScope.launch {

                withContext(Dispatchers.IO) {
                    turnOnFlash()
                }

                delay(durationFlashOn(char))

                withContext(Dispatchers.IO) {
                    turnOffFlash()
                }

                delay(durationFlashOff(char))
            }
        }

    }

    private fun durationFlashOn(char: Char): Long {
        return when (char) {
            DOT -> DOT_DURATION
            STRIP -> STRIP_DURATION
            else -> DEFAULT_DURATION
        }
    }

    private fun durationFlashOff(char: Char): Long {
        return when (char) {
            SPACE -> SPACE_DURATION
            else -> DEFAULT_DURATION
        }
    }

    private fun turnOnFlash() {
        setLog("turnOnFlash")
        switchFlashLight(true)
    }

    private fun turnOffFlash() {
        setLog("turnOffFlash")
        switchFlashLight(false)
    }

    private fun switchFlashLight(isFlashOn: Boolean) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, isFlashOn)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun showNoFlashNotAvailable() {
        val alert = AlertDialog.Builder(this)
            .create()
        alert.setTitle("Oops!")
        alert.setMessage(getString(R.string.flash_not_available_in_this_device))
        alert.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.core_ok)) { _, _ -> finish() }
        alert.show()
    }

    private fun setLog(msg: String) {
        Log.e("flash", msg)
    }

}