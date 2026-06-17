package com.pedro.pideyaapp.data.repository

import android.content.Context
import com.pedro.pideyaapp.data.localization.localizedString

class StringsProvider(
    private val context: Context
) {
    fun get(resId: Int): String = context.localizedString(resId)
}
