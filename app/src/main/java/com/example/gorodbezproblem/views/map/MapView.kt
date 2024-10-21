import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gorodbezproblem.R

@Composable
fun MyXMLLayout() {
    AndroidView(
        factory = { context ->
            // Раздуваем (inflate) XML-разметку и возвращаем ее как View
            LayoutInflater.from(context).inflate(R.layout.activity_main, null)
        }/*,
        update = { view ->
            // Дополнительно можно обновить элементы в разметке
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = "Updated from Compose"
        }*/
    )
}
