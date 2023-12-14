package com.d121211065.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211065.tmdb.data.models.Result
import com.d121211065.tmdb.ui.theme.TMDBTheme


class DetailActivity : ComponentActivity() {

    private var selectedMovie: Result ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMovie = intent.getParcelableExtra("Movie DB")
        setContent {
            TMDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieDetailScreen()
                }
            }
        }
    }

    @Composable
    fun MovieDetailScreen() {

        val baseImageUrl = "https://image.tmdb.org/t/p/w200"

        val posterPath = selectedMovie?.poster_path

        val imageUrl = if (!posterPath.isNullOrEmpty()) {
            "$baseImageUrl$posterPath"
        } else {
             null
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Character Image
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedMovie?.original_title,
                modifier = Modifier
                    .width(400.dp)
                    .height(600.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Character Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = selectedMovie?.original_title ?: "Unknown", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Release: ${selectedMovie?.release_date ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: ${selectedMovie?.vote_average ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Popularity: ${selectedMovie?.popularity ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language: ${selectedMovie?.original_language ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Sinopsis: ${selectedMovie?.overview?: "Unknown"}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}