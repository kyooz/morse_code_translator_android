package com.morsecodetranslator.common

import android.app.Activity
import com.morsecodetranslator.R

inline fun <reified T : Activity> Activity.startActivityLeftTransition(vararg params: Pair<String, Any?>) {
    startActivity(createIntent(this, T::class.java, params))
    slideInLeftTransition()
}

fun Activity.slideInLeftTransition() {
    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}
