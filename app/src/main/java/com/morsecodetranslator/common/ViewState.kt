package com.morsecodetranslator.common

sealed class ViewState<T>  {
    data class Loading<T>(var progress: Float? = null) : ViewState<T>()
    data class Success<T>(var data: T) : ViewState<T>()
    data class Error<T>(var viewError: String? = null) : ViewState<T>()
}