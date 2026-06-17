package com.pedro.pideyaapp.data.localization

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate

fun Context.localizedString(@StringRes resId: Int): String {
    val languageTags = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    if (languageTags.isBlank()) {
        return getString(resId)
    }

    val configuration = Configuration(resources.configuration)
    configuration.setLocales(LocaleList.forLanguageTags(languageTags))
    return createConfigurationContext(configuration).resources.getString(resId)
}
