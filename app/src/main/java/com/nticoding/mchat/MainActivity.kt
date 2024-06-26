package com.nticoding.mchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nticoding.mchat.domain.model.Conversation
import com.nticoding.mchat.presentation.chat.ChatScreen
import com.nticoding.mchat.ui.theme.MChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // NOTE: In a real app these would likely be passed as navigation arguments
                    // hardcoded here for simplicity
                    ChatScreen(
                        Conversation(
                            0,
                            "Sarah"
                        )
                    )
                }
            }
        }
    }
}