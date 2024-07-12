import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import i18n.Language
import org.apache.commons.collections4.bidimap.TreeBidiMap



@Composable
@Preview
fun App() {
    val fileDir: MutableState<String> = remember { mutableStateOf("choose file") }
    val canSelectFile: MutableState<Boolean> = remember { mutableStateOf(false) }
    val sourceChoose: MutableState<String> = remember { mutableStateOf("官方源") }
    val checkBoolean: MutableState<Boolean> = remember { mutableStateOf(false) }
    val language: MutableState<String> = remember { mutableStateOf(Language.EnUs.name) }
    val sourceSelect = remember { mutableStateOf(TreeBidiMap(mapOf("官方源" to "0", "BM-CL源" to "1"))) }
    config(sourceChoose, checkBoolean, fileDir, language)
    val lang = remember { Language.valueOf(config!!.language) }
    val get = lang.supplier.get()
    sourceSelect.value = TreeBidiMap(mapOf(get.get("sources.0")!! to "0", get.get("sources.1")!! to "1"))
    sourceChoose.value = sourceSelect.value.getKey(config!!.sourceHome)

    MaterialTheme {
        top(sourceSelect, sourceChoose, lang)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                isClient(checkBoolean, lang)
            }
            chooseFile(canSelectFile, fileDir)
            download()
        }

    }

}



fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
