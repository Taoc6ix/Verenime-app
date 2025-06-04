package com.taoc.verenime.ui.streaming

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button

@Composable
fun StreamingScreen(
    animeId: String,
    episodeId: String,
    onBack: () -> Unit
) {
    val viewModel: StreamingViewModel = viewModel()
    val episode by viewModel.episodeData.collectAsState()
    val serverData by viewModel.serverData.collectAsState()

    LaunchedEffect(episodeId) {
        viewModel.fetchEpisode(episodeId)
    }

    LaunchedEffect(animeId) {
        viewModel.fetchAnimeDetail(animeId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A20))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Video Player
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    AndroidView(
                        factory = {
                            WebView(it).apply {
                                settings.javaScriptEnabled = true
                                loadUrl(episode?.data?.defaultStreamingUrl ?: "")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Judul dan Episode
            item {
                Text(
                    text = episode?.data?.title.orEmpty(),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Server Streaming
            serverData?.qualities?.forEach { quality ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {
                        quality.serverList.forEach { server ->
                            Button(
                                onClick = { viewModel.fetchServer(server.serverId) },
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text(server.title, color = Color.White)
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Episode Lainnya",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Daftar Episode (List utama)
            items(episode?.data?.episodeList ?: emptyList()) { ep ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable { viewModel.fetchEpisode(ep.id) }
                ) {
                    Text(
                        ep.title,
                        modifier = Modifier.padding(12.dp),
                        color = Color.White
                    )
                }
            }
        }

        // Tombol Kembali
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
    }
}


