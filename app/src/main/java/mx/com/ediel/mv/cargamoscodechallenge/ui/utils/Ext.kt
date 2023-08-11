package mx.com.ediel.mv.cargamoscodechallenge.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toCustomerFormat(): String{
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val outPutFormatter = SimpleDateFormat("dd/MM/yyyy")
    return outPutFormatter.format(formatter.parse(this))
}