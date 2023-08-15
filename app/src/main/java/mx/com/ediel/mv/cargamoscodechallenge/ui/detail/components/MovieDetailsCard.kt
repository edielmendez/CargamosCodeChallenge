package mx.com.ediel.mv.cargamoscodechallenge.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import mx.com.ediel.mv.cargamoscodechallenge.R
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.utils.toCustomerFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailsCard(
    modifier: Modifier = Modifier,
    movie: Movie
){
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.test_image),
            contentDescription = movie.title,
            contentScale = ContentScale.Fit
        )*/
        GlideImage(modifier = Modifier
            .fillMaxWidth(),
            model = movie.poster,
            contentDescription = movie.title,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = movie.releaseDate.toCustomerFormat(),
            color = Color.DarkGray,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )
        Text(
            modifier = Modifier.padding(all = 4.dp),
            text = movie.title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            text = movie.averageLabel,
            color = Color.DarkGray,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )
        LinearProgressIndicator(progress = 0.85f)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(all = 4.dp),
            text = movie.resume,
            color = Color.Gray,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp
        )
    }
}