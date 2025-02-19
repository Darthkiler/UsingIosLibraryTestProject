package org.example.project.ui.photolistscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.domain.Photo
import org.example.project.ui.components.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import usingioslibrarytestproject.composeapp.generated.resources.Res
import usingioslibrarytestproject.composeapp.generated.resources.photo_list_empty
import usingioslibrarytestproject.composeapp.generated.resources.photo_list_error
import usingioslibrarytestproject.composeapp.generated.resources.retry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen(
    viewModel: PhotoListScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "PhotoList")
                },
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
        ) {
            when {
                uiState.isLoading -> {
                    Loading()
                }

                uiState.isError -> {
                    Error(
                        onRetryClick = {
                            viewModel.onRetryClick()
                        }
                    )
                }

                uiState.list.isEmpty() -> {
                    EmptyList()
                }

                else -> {
                    PhotoList(list = uiState.list)
                }
            }
        }
    }
}

@Composable
private fun PhotoList(
    modifier: Modifier = Modifier,
    list: List<Photo>
) {
    LazyVerticalGrid(
        modifier = modifier.sizeIn(maxWidth = 450.dp).padding(16.dp),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        items(list) { item ->
            Card(
                shape = RoundedCornerShape(24.dp),
            ) {
                AsyncImage(
                    modifier = Modifier.size(width = 200.dp, height = 300.dp),
                    url = item.url,
                )
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
    }
}

@Composable
private fun Error(
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            8.dp, alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = stringResource(Res.string.photo_list_error),
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = onRetryClick) {
            Text(
                text = stringResource(Res.string.retry),
            )
        }
    }
}

@Composable
private fun EmptyList() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.photo_list_empty),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}