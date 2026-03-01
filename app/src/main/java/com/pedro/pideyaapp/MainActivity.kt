package com.pedro.pideyaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pedro.pideyaapp.ui.navigation.AppNavigation
import com.pedro.pideyaapp.ui.theme.PideYaAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PideYaAppTheme {
                AppNavigation()
            }
        }
    }
}