package com.pedro.pideyaapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.Surface
import androidx.core.os.LocaleListCompat
import com.pedro.pideyaapp.data.local.UserPreferences
import com.pedro.pideyaapp.ui.navigation.AppNavigation
import com.pedro.pideyaapp.ui.theme.PideYaTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(applicationContext)
        val currentLanguage = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val effectiveLanguage = currentLanguage.ifBlank { userPreferences.getLanguage() }

        if (currentLanguage.isBlank()) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(effectiveLanguage)
            )
        } else if (currentLanguage != userPreferences.getLanguage()) {
            userPreferences.saveLanguage(currentLanguage)
        }

        setContent {
            PideYaTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }
}
