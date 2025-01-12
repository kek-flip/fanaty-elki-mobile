package com.example.gorodbezproblem.views.location

import androidx.lifecycle.ViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class LocationViewModel : ViewModel() {
    var currentAddress: String = ""
        private set

    fun updateAddress(newAddress: String) {
        currentAddress = newAddress
    }

    fun zoomToMoscow(mapView: MapView) {
        val moscowLocation = Point(55.755394, 37.635131) // СЮДА ВВОДИТЬ ФЕЙК МЕСТОПОЛОЖЕНИЕ
        val cameraPosition = CameraPosition(moscowLocation, 20.0f, 0.0f, 0.0f)
        mapView.map.move(cameraPosition, Animation(Animation.Type.SMOOTH, 1f), null)
    }
}
