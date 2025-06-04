package com.taoc.verenime.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.taoc.verenime.ui.ExpandableText
import com.taoc.verenime.R

    @Composable
    fun DetailScreen(
        viewModel: DetailViewModel,
        animeId: String,
        onBack: () -> Unit = {},
        onEpisodeClick: (String) -> Unit
    ) {
        val detail by viewModel.animeDetail.collectAsState()

        LaunchedEffect(animeId) {
            viewModel.getAnimeDetail(animeId)
        }

    detail?.let { anime ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Background blur
            Image(
                painter = rememberAsyncImagePainter(anime.poster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .blur(30.dp),
                contentScale = ContentScale.Crop,
                alpha = 0.7f
            )

            Column(
                modifier = Modifier
                    .background(Color(0xFF181A20))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 15.dp)
            ) {
                // Arrow back
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
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

                Spacer(modifier = Modifier.height(10.dp))

                // Poster anime (depan)
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(anime.poster),
                        contentDescription = anime.title,
                        modifier = Modifier
                            .height(220.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Judul, skor, status
                Text(
                    anime.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 4.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⭐ ${anime.score ?: "-"}", color = Color.Yellow, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(anime.status ?: "-", color = Color.White)
                }

                // Sinopsis Expandable
                ExpandableText(
                    text = anime.synopsis?.paragraphs?.joinToString("\n\n") ?: "-",
                    collapsedMaxLines = 4,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Episode list
                Text(
                    "Episode",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp) // agar bisa discroll sampai bawah
                        .padding(horizontal = 24.dp)
                ) {
                    items(anime.episodeList ?: emptyList()) { episode ->
                        if (episode.id != null) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable { onEpisodeClick(episode.id) },
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White.copy(
                                        alpha = 0.1f
                                    )
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(episode.title ?: "▶\uFE0F", color = Color.White)
                                    Text(
                                        episode.date ?: "▶\uFE0F",
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}