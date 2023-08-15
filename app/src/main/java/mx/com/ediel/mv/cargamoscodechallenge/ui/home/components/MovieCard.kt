package mx.com.ediel.mv.cargamoscodechallenge.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.ediel.mv.cargamoscodechallenge.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.utils.toCustomerFormat
import java.time.format.TextStyle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onItemClick:  (Movie) -> Unit
){
    Card(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onItemClick(movie) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(160.dp)
            )
            //Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(all = 4.dp),
                text = movie.title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                text = movie.releaseDate.toCustomerFormat(),
                color = Color.DarkGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}