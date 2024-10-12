package com.example.gorodbezproblem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("c60d9d3f-42be-4fa6-a050-8f39bb153dce")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapview)

        setContent {
            // Использование стандартной темы Material Design 3
            MaterialTheme {
                MainScreen()  // Подключение основной логики навигации
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen()
    }
}

