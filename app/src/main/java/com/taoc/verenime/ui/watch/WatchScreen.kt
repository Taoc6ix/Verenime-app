package com.taoc.verenime.ui.watch

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.taoc.verenime.R
import com.taoc.verenime.api.responses.ServerListItem

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WatchScreen(
    episodeId: String,
    viewModel: WatchViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(episodeId) {
        viewModel.loadWatchData(episodeId)
    }

    when (uiState) {
        is WatchUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.51f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        is WatchUiState.Error -> {
            val message = (uiState as WatchUiState.Error).message
            Text(text = "Error: $message", color = Color.White, modifier = Modifier.padding(16.dp))
        }

        is WatchUiState.Success -> {
            val data = (uiState as WatchUiState.Success).data
            val videoUrl = viewModel.currentStreamingUrl

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF181A20))
            ) {
//                AndroidView(
//                    factory = { context ->
//                        WebView(context).apply {
//                            webViewClient = WebViewClient()
//                            settings.javaScriptEnabled = true
//                            if (!videoUrl.isNullOrEmpty()) {
//                                loadUrl(videoUrl)
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(220.dp)
//                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = data?.title ?: "",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Grid-like quality buttons
                val qualities = data?.server?.qualities?.flatMap { quality ->
                    quality?.urls?.mapNotNull { it?.url?.let { url -> quality.title to url } } ?: emptyList()
                } ?: emptyList()

//                FlowRow(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    qualities.forEach { (title, url) ->
//                        Button(
//                            onClick = {
//                                viewModel.switchServer(serverId = url, episodeId = episodeId)
//                            },
//                            modifier = Modifier
//                                .defaultMinSize(minWidth = 120.dp),
//                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
//                        ) {
//                            Text(title.toString(), color = Color.White)
//                        }
//                    }
//                }

                Spacer(modifier = Modifier.height(12.dp))
                // Episode List - vertical scroll
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    items(data?.info?.episodeList ?: emptyList()) { episode ->
                        episode?.episodeId?.let { epId ->
                            Button(
                                onClick = { viewModel.loadWatchData(epId) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (epId == episodeId) Color.Gray else Color.DarkGray
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                shape = RoundedCornerShape(8.dp),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Episode ${episode.title}",
                                        color = Color.White
                                    )
                                    Text(
                                        text = "▶\uFE0F",
                                        color = Color.White,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
