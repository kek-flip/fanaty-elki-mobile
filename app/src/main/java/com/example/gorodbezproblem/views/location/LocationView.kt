package com.example.gorodbezproblem.views.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.R
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.views.map.AddressMapView
import com.example.gorodbezproblem.views.location.LocationViewModel
import com.yandex.mapkit.mapview.MapView

@Composable
fun LocationScreen(navController: NavHostController, viewModel: LocationViewModel = viewModel()) {
    var address by remember { mutableStateOf(TextFieldValue(viewModel.currentAddress)) }
    var mapViewReference: MapView? by remember { mutableStateOf(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Карта на заднем плане
        AddressMapView(
            onMapClick = { coordinates ->
                // Обновляем текстовое поле с координатами
                address = TextFieldValue(coordinates)
                viewModel.updateAddress(coordinates) // Сохраняем координаты в LocationViewModel
            },
            onZoomToMoscow = { mapView -> mapViewReference = mapView }
        )

        // кнопка местоположения
        Button(
            onClick = {
                mapViewReference?.let { viewModel.zoomToMoscow(it) }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen),
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 16.dp, end = 16.dp, bottom = 175.dp) // Отступ от нижнего блока
                .size(72.dp) //размер кнопки
        ) {
            Image(
                painter = painterResource(id = R.drawable.my_location),
                contentDescription = "Иконка местоположения",
                modifier = Modifier
                    .size(48.dp) // Увеличиваем размер изображения
            )
        }


        // Блок с текстовым полем и кнопкой "Готово"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Введите адрес") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Передаем текст из текстового поля в SavedStateHandle
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedAddress", address.text)
                    navController.popBackStack() // Возвращаемся назад
                },
                colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Готово", color = Color.White)
            }

        }
    }
}




