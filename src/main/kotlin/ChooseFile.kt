import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import java.util.Arrays
import kotlin.io.path.*

@Composable
fun chooseFile() {
    val canSelectFile = remember { mutableStateOf(false) }
    ProvideStrings(lyricist) {
        val chooseTitle = LocalStrings.current.chooseTitle
        val noChooseTitle = LocalStrings.current.noChooseTitle
        val lastFileDir = Path(config.lastSelectFile)
        val chooseFileTitle = remember { mutableStateOf(
            if (lastFileDir.exists() && lastFileDir.isDirectory().not()) {
                chooseTitle + config.lastSelectFile
            } else {
                noChooseTitle
            }
        )
        }
        Row {
            TextButton(
                onClick = {
                    canSelectFile.value = true
                },
                enabled = true,
                modifier = Modifier.size(800.dp,40.dp)
            ) {
                Text(chooseFileTitle.value)
            }
        }
        if (canSelectFile.value) {
            selectFile(canSelectFile, chooseFileTitle, chooseTitle, noChooseTitle)
        }
    }


}

@Composable
fun selectFile(
    canSelectFile: MutableState<Boolean>,
    chooseFileTitle: MutableState<String>,
    chooseTitle: String,
    noChooseTitle: String
) {

    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {

            val a = when(config.mode) {
                "0" -> listOf("csv")
                "1" -> listOf("zip")
                else -> listOf("mrpack")
            }
            FilePicker(
                show = canSelectFile.value,
                fileExtensions = a
            ) {
                if (it != null) {
                    config.lastSelectFile = it.path
                    val path = Path(config.lastSelectFile)
                    chooseFileTitle.value = if (path.exists() && path.isDirectory().not()) {
                        chooseTitle + config.lastSelectFile
                    } else {
                        noChooseTitle
                    }
                    configPath.writeText(gson.toJson(config), Charsets.UTF_8)
                }


                canSelectFile.value = false
            }


        }
    }
}