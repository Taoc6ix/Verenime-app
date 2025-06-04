package com.taoc.verenime.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.taoc.verenime.R
import com.taoc.verenime.ui.AnimeCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onAnimeClick: (String) -> Unit = {},
    onBack: () -> Unit = {},
) {
    var query by remember { mutableStateOf("") }
    val result by viewModel.searchResult.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF181A20))
        .padding(12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), // ganti dengan icon arrow back kamu
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.searchAnime(query)
            },
            label = { Text("Cari Anime di sini") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(result) { anime ->
                AnimeCard(
                    anime = anime,
                    onClick = { onAnimeClick(anime.animeId) }
                )
            }
        }
    }
}