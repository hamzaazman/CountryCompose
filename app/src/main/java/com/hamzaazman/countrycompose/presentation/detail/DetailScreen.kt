package com.hamzaazman.countrycompose.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.hamzaazman.countrycompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel(),
    nameArgs: String,
) {

    LaunchedEffect(nameArgs) {
        detailViewModel.getCountryByName(nameArgs)
    }

    val detailState by detailViewModel.detailUiState.collectAsStateWithLifecycle()

    when (detailState) {
        is DetailUiState.Error -> {
            Text(text = (detailState as DetailUiState.Error).message.toString())
        }

        is DetailUiState.Loading -> {
            LoadingIndicator()
        }

        is DetailUiState.Success -> {
            val data = (detailState as DetailUiState.Success).data

            Scaffold(modifier = Modifier.padding(), topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = data.name?.common?.take(35).toString(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            },
                        ) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                )
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .decoderFactory(SvgDecoder.Factory())
                            .data(data.flags?.svg)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.flag_placeholder),
                        contentDescription = data.flags?.alt,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    )
                    Column {
                        Row {
                            Text(text = "Not Finished Yet!!!")
                        }
                    }
                }
            }

        }
    }


}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    }
}
