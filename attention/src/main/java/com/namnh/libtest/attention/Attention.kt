package com.namnh.libtest.attention

import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.namnh.libtest.core.longToast
import com.namnh.libtest.core.shortToast

object Attention {
    fun shortToast(context: Context, message: String) {
        context.shortToast(message)
    }
    fun longToast(context: Context, message: String) {
        context.longToast(message)
    }
}