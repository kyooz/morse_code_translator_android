package com.morsecodetranslator.view.base

import android.view.LayoutInflater
import android.view.ViewGroup

typealias CustomInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T