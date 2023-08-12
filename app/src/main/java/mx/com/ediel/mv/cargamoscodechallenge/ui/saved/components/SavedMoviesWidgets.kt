package mx.com.ediel.mv.cargamoscodechallenge.ui.saved.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoSearchResultsFounds(
    searchText: String
) {

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "No se encontraron resultados para la busqueda",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Text(
            text = "\"$searchText\"",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )

    }
}

@Composable
@Preview(showSystemUi = true)
fun NoSearchResultsFoundsPreview() {
    NoSearchResultsFounds(
        "busqueda"
    )
}