package com.masimo.media.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.masimo.media.R
import com.masimo.media.data.MainViewModel
import com.masimo.media.ui.view.play.NowPlayScreen
import com.masimo.media.ui.view.room.RoomsScreen
import com.masimo.media.ui.view.set.SettingsScreen

@Composable
fun TabLayout(viewModel: MainViewModel) {
    val tabIndex = viewModel.tabIndex.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex.value!!) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex.value!! == index,
                    onClick = { viewModel.updateTabIndex(index) },
                    icon = {
                        when (index) {
                            0 -> Icon(modifier = Modifier.size(25.dp), painter = painterResource(R.drawable.tabbar_icon_block_rooms), contentDescription = null)
                            1 -> Icon(modifier = Modifier.size(25.dp), painter = painterResource(R.drawable.tabbar_icon_now_playing), contentDescription = null)
                            2 -> Icon(modifier = Modifier.size(25.dp), painter = painterResource(R.drawable.tabbar_icon_settings), contentDescription = null)
                        }
                    }
                )
            }
        }

        when (tabIndex.value) {
            0 -> RoomsScreen(viewModel = viewModel)
            1 -> NowPlayScreen(viewModel = viewModel)
            2 -> SettingsScreen(viewModel = viewModel)
        }
    }
}