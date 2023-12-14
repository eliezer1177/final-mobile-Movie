package com.d121211065.tmdb.ui.activity;

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211065.tmdb.data.models.Result
import com.d121211065.tmdb.ui.activity.DetailActivity
import com.d121211065.tmdb.ui.activity.MainUiState
import com.d121211065.tmdb.ui.activity.MainViewModel
import com.d121211065.tmdb.ui.theme.TMDBTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                    ListMovieScreen(mainViewModel.mainUiState)
                }
            }
        }
    }

    @Composable
    private fun ListMovieScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when (mainUiState) {
            is MainUiState.Loading -> Text(text = "Sedang Loading", fontSize = 16.sp)
            is MainUiState.Error -> Text(text = "Terjadi Error", fontSize = 16.sp)
            is MainUiState.Success -> CharacterList(mainUiState.movies)
        }
    }

    @Composable
    fun CharacterList(characters: List<Result>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(characters) { pilem ->
                PilmInfo(pilem = pilem)
            }
        }
    }

    @Composable
    fun PilmInfo(pilem: Result) {
        val baseImageUrl = "https://image.tmdb.org/t/p/w200"

        val posterPath = pilem.poster_path

        val imageUrl = "$baseImageUrl$posterPath"


        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp),)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("Movie DB", pilem)
                    startActivity(intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Character Image
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = pilem.original_title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                // Character Details
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pilem.original_title ?: "Unknown",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rating: ${pilem.vote_average ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Release: ${pilem.release_date ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall
                )
                // Other details like affinity, background, etc. can be added here
            }
        }
    }
}

