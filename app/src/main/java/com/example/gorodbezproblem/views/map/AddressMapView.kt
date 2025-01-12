package com.example.gorodbezproblem.views.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorodbezproblem.MainActivity
import com.example.gorodbezproblem.R
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.modules.getAuthToken
import com.example.gorodbezproblem.views.map.MapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun AddressMapView(
    onMapClick: (String) -> Unit, // Callback для передачи координат в текстовом виде
    onZoomToMoscow: (MapView) -> Unit
) {
    AndroidView(
        factory = { context ->
            val mapView = MapView(context)

            // Устанавливаем камеру на Москву
            val targetLocation = Point(55.751244, 37.618423)
            val cameraPosition = CameraPosition(targetLocation, 12.0f, 0.0f, 0.0f)

            mapView.map.move(
                cameraPosition,
                Animation(Animation.Type.SMOOTH, 1f),
                null
            )

            // Обработка нажатий на карту
            mapView.map.addInputListener(object : com.yandex.mapkit.map.InputListener {
                override fun onMapTap(map: com.yandex.mapkit.map.Map, point: Point) {
                    // Формируем строку с координатами
                    val coordinates = "${point.latitude}, ${point.longitude}"
                    onMapClick(coordinates) // Передаем строку с координатами
                }

                override fun onMapLongTap(map: com.yandex.mapkit.map.Map, point: Point) {
                    // Можно добавить обработку длинного нажатия, если нужно
                }
            })

            mapView
        },
        modifier = Modifier.fillMaxSize(),
        update = { mapView ->
            onZoomToMoscow(mapView) // Callback для зума
        }
    )
}




