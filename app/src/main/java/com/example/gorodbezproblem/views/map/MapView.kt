import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gorodbezproblem.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun MyMapView() {
    AndroidView(
        factory = { context ->
            // Создаем MapView напрямую, без использования XML
            val mapView = MapView(context)

            // Устанавливаем камеру на Москву
            val targetLocation = Point(55.751244, 37.618423)
            val cameraPosition = CameraPosition(targetLocation, 12.0f, 0.0f, 0.0f)

            mapView.map.move(
                cameraPosition,
                Animation(Animation.Type.SMOOTH, 1f),
                null
            )

            // Добавляем метку с иконкой на карте и уменьшаем размер иконки
            val imageProvider = ImageProvider.fromResource(context, R.drawable.ic_pin)
            val placemark = mapView.map.mapObjects.addPlacemark(Point(55.765984, 37.684618)).apply {
                setIcon(imageProvider, IconStyle().apply {
                    scale = 0.5f // Масштаб иконки, 0.5f уменьшит ее в 2 раза
                })
            }

            // Возвращаем созданный mapView
            mapView
        },
        modifier = Modifier.fillMaxSize()
    )
}
