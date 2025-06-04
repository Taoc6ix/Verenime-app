package com.taoc.verenime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taoc.verenime.ui.detail.DetailScreen
import com.taoc.verenime.ui.detail.DetailViewModel
import com.taoc.verenime.ui.home.HomeScreen
import com.taoc.verenime.ui.home.HomeViewModel
import com.taoc.verenime.ui.search.SearchScreen
import com.taoc.verenime.ui.search.SearchViewModel
import com.taoc.verenime.ui.streaming.StreamingScreen
import com.taoc.verenime.ui.theme.VerenimeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerenimeTheme {
                val navController = rememberNavController()
                val homeViewModel: HomeViewModel = viewModel()
                val detailViewModel: DetailViewModel = viewModel()
                val searchViewModel: SearchViewModel = viewModel()


                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onSearchClick = { navController.navigate("search") },
                            onOngoingClick = {},
                            onAnimeClick = { animeId ->
                                navController.navigate("detail/$animeId")
                            }
                        )
                    }
                    composable("detail/{animeId}") { backStackEntry ->
                        val animeId = backStackEntry.arguments?.getString("animeId") ?: ""
                        DetailScreen(
                            viewModel = detailViewModel,
                            animeId = animeId,
                            onBack = { navController.popBackStack() },
                            onEpisodeClick = { episodeId ->
                                navController.navigate("streaming/$animeId/$episodeId")
                            }
                        )
                    }
                    composable("search") {
                        SearchScreen(
                            viewModel = searchViewModel,
                            onAnimeClick = { animeId ->
                                navController.navigate("detail/$animeId")
                            }
                        )
                    }
                    composable("streaming/{animeId}/{episodeId}") { backStackEntry ->
                        val animeId = backStackEntry.arguments?.getString("animeId") ?: ""
                        val episodeId = backStackEntry.arguments?.getString("episodeId") ?: ""
                        StreamingScreen(animeId = animeId, episodeId = episodeId, onBack = { navController.popBackStack() })
                    }

                }
            }
        }
    }
}