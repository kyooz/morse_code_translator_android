package com.morsecodetranslator.view.comunication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.morsecodetranslator.R
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityFlashComunicationBinding
import com.morsecodetranslator.view.base.BaseActivity
import kotlinx.coroutines.*

class FlashCommunicationActivity : BaseActivity() {

    private val binding by viewBinding(ActivityFlashComunicationBinding::inflate)
    private lateinit var viewModel : FlashCommunicationViewModel

    // flash permission
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    companion object {
        val MESSAGE_ARG = "MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FlashCommunicationViewModel::class.java)

        setUpFlash()
        startObservingData()
        getIntentData()

    }

    override fun onStart() {
        super.onStart()
        setUpFlash()
    }

    private fun getIntentData() {

        val message = intent.getStringExtra(MESSAGE_ARG)

        if (isFlashAvailable()) {
            viewModel.startFlash(message ?: "")
        } else {
            showNoFlashNotAvailable()
        }

    }

    private fun startObservingData() {

        viewModel.flashState.observe(this) {
            when(it) {
                is ViewState.Success -> {
                    if (it.data) {
                        flashOn()
                    } else {
                        flashOff()
                    }
                }
            }
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

    private fun flashOn() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true)
            }
        } catch (e: CameraAccessException) {
            // error message
        }
    }

    private fun flashOff() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false)
            }
        } catch (e: CameraAccessException) {
            // error message
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

}