package mx.com.ediel.mv.cargamoscodechallenge.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import mx.com.ediel.mv.cargamoscodechallenge.R

@Composable
fun Loader(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.point_loader))
    //val progress by animateLottieCompositionAsState(composition)
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}