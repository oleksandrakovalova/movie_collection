package com.okproject.moviecollection.ui.screens.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.okproject.moviecollection.R
import com.okproject.moviecollection.ui.screens.NavigationRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentSize(),
                        painter = painterResource(R.drawable.blue_long),
                        contentDescription = null
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        PopularMoviesContent(
            navController = navController,
            scope = coroutineScope,
            snackbarHostState = snackbarHostState,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun PopularMoviesContent(
    navController: NavController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    viewModel: PopularMoviesViewModel = koinViewModel()
) {
    val pagedPopularMovies = viewModel.movieState.collectAsLazyPagingItems()
    val errorMessage = stringResource(id = R.string.unable_to_load_data)
    val retryAction = stringResource(id = R.string.retry)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.padding(8.dp)
    ) {

        items(pagedPopularMovies.itemCount) {index ->
            val movie = pagedPopularMovies[index]!!
            Box(modifier = Modifier.padding(8.dp)) {
                MovieCard(
                    item = movie,
                    onClick = {
                        navController.navigate(
                            NavigationRoute.Movie.direction(movieId = movie.id)
                        )
                    }
                )
            }
        }
        pagedPopularMovies.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = errorMessage,
                                actionLabel = retryAction,
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                retry()
                            }
                            SnackbarResult.Dismissed -> {}
                        }
                    }
                }
            }
        }
    }
}