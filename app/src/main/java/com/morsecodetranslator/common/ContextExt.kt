package com.morsecodetranslator.common

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.morsecodetranslator.R


fun Context.copyToClipboard(label: String, value: String, customToast: (() -> Unit)? = null) {
    try {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(label, value)
        clipboardManager.setPrimaryClip(clipData)

        if (customToast == null) {
            Toast.makeText(this, getString(R.string.core_copied), Toast.LENGTH_SHORT).show()
        } else {
            customToast()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}