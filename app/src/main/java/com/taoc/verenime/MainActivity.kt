package com.taoc.verenime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taoc.verenime.api.service.ApiService
import com.taoc.verenime.ui.detail.DetailScreen
import com.taoc.verenime.ui.detail.DetailViewModel
import com.taoc.verenime.ui.home.HomeScreen
import com.taoc.verenime.ui.home.HomeViewModel
import com.taoc.verenime.ui.search.SearchScreen
import com.taoc.verenime.ui.search.SearchViewModel
import com.taoc.verenime.ui.theme.VerenimeTheme
import com.taoc.verenime.ui.watch.WatchRepository
import com.taoc.verenime.ui.watch.WatchScreen
import com.taoc.verenime.ui.watch.WatchViewModel
import com.taoc.verenime.ui.watch.WatchViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerenimeTheme {
                val navController = rememberNavController()
                val homeViewModel: HomeViewModel = viewModel()
                val detailViewModel: DetailViewModel = viewModel()
                val searchViewModel: SearchViewModel = viewModel()
                val watchViewModelFactory = WatchViewModelFactory(WatchRepository(ApiService.create()))
                val watchViewModel: WatchViewModel = viewModel(factory = watchViewModelFactory)


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
                        val episodeId = backStackEntry.arguments?.getString("episodeId") ?: ""
                        WatchScreen(
                            episodeId = episodeId,
                            viewModel = watchViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}