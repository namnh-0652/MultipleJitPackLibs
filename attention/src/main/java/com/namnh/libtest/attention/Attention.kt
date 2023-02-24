package com.namnh.libtest.attention

import android.content.Context
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