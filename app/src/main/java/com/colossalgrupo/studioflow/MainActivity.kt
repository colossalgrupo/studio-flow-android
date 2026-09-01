package com.colossalgrupo.studioflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.colossalgrupo.studioflow.ui.navigation.StudioScheduleNavHost
import com.colossalgrupo.studioflow.ui.theme.StudioScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudioScheduleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StudioScheduleNavHost()
                }
            }
        }
    }
}
