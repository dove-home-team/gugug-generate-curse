import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import com.mrshiehx.cmcl.CMCL

@Composable
fun download() {
    ProvideStrings(lyricist) {
        Row {
            Button(    onClick = {
                CMCL.main(arrayOf("install", "-h"))
            }) {
                Text(LocalStrings.current.downloadClientTitle)
            }
            Text("   ")
            Button(    onClick = {
                CMCL.main(arrayOf("install", "-h"))
            }) {
                Text(LocalStrings.current.downloadServerTitle)
            }
        }
    }

}