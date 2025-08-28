package ru.pk.neuronetestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.presentation.navigation.SetupNavHost
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this.applicationContext
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(
                scrim = context.getColor(R.color.transparent),
            ),
        )
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
        setContent {
            val navController = rememberNavController()
            NeuroneTestAppTheme {
                SetupNavHost(navController)
            }
        }
    }
}