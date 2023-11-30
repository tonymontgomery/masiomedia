package com.masimo.media

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.masimo.media.data.MainViewModel
import com.masimo.media.ui.theme.MasimoMediaTheme
import com.masimo.media.ui.view.TabLayout

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasimoMediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    TabLayout(viewModel = viewModel)
                }
            }
        }

        App().fetchRoomsList(viewModel, viewModel.roomList, App.appContext)
    }
}
