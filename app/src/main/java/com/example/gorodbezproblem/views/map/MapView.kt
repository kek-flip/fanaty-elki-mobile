import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorodbezproblem.R
import com.example.gorodbezproblem.models.Problem
import com.example.gorodbezproblem.views.map.MapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import androidx.compose.runtime.remember

@Composable
fun MyMapView() {
    val context = LocalContext.current
    // Получаем MapViewModel с помощью viewModel() без необходимости передавать контекст
    val mapViewModel: MapViewModel = viewModel()

    LaunchedEffect(Unit) {
        mapViewModel.loadProblems()
    }

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

            val imageProvider = ImageProvider.fromResource(context, R.drawable.ic_pin)

            mapViewModel.problems.forEach { problem: Problem ->
                mapView.map.mapObjects.addPlacemark(
                    Point(
                        problem.lat.toDouble(),
                        problem.long.toDouble()
                    )
                ).apply {
                    setIcon(imageProvider, IconStyle().apply {
                        scale = 0.5f
                    })
                }
            }

            mapView
        },
        modifier = Modifier.fillMaxSize()
    )
}
