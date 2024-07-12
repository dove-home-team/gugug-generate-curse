import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.mrshiehx.cmcl.CMCL

@Composable
fun download() {
    Row {
        Button(    onClick = {
            CMCL.main(arrayOf("install", "-h"))
        }) {
            Text("下载")
        }
    }
}