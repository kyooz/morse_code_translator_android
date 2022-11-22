package com.morsecodetranslator.view.comunication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.morsecodetranslator.R
import com.morsecodetranslator.common.ViewState
import com.morsecodetranslator.common.viewBinding
import com.morsecodetranslator.databinding.ActivityFlashComunicationBinding
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity


class FlashCommunicationActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityFlashComunicationBinding::inflate)
    private lateinit var viewModel : FlashCommunicationViewModel

    // data
    private var isTimerStart = false
    private var morseMessage: String? = ""

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
        initListener()

    }

    override fun onStart() {
        super.onStart()
        setUpFlash()
    }

    private fun getIntentData() {

        morseMessage = intent.getStringExtra(MESSAGE_ARG)

        if (isFlashAvailable()) {
            viewModel.startFlash(morseMessage ?: "")
        } else {
            showNoFlashNotAvailable()
        }

    }

    private fun initListener() {

        binding.ivFlashLight.setOnClickListener {
            finish()
        }

    }

    private fun startObservingData() {

        viewModel.flashState.observe(this) {
            when(it) {
                is ViewState.Success -> {

                    // check timer start
                    // if timer countdown not start
                    // start timer count down
                    if (!isTimerStart) {
                        startTimerCountDown(viewModel.countDuration)
                        // update status timer
                        // so that countdown doesn't run anymore
                        isTimerStart = true
                    }

                    // flash
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

    private fun startTimerCountDown(secondDuration: Long) {
        object : CountDownTimer(secondDuration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val duration = millisUntilFinished / 1000
                binding.tvDuration.text = "$duration"
            }

            override fun onFinish() {
                binding.tvDuration.text = getString(R.string.done)
            }
        }.start()

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