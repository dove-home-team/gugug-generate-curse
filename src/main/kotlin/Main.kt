import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.lyricist.Lyricist

import i18n.Locales
import i18n.strings.Strings

import org.apache.commons.collections4.bidimap.TreeBidiMap


lateinit var lyricist: Lyricist<Strings>

@Composable
@Preview
fun App() {

    val fileDir: MutableState<String> = remember { mutableStateOf("choose file") }
    val canSelectFile: MutableState<Boolean> = remember { mutableStateOf(false) }
    val sourceChoose: MutableState<String> = remember { mutableStateOf("官方源") }
    val checkBoolean: MutableState<Boolean> = remember { mutableStateOf(false) }
    val language: MutableState<String> = remember { mutableStateOf(Locales.EN) }

    val sourceSelect = remember { mutableStateOf(TreeBidiMap(mapOf("官方源" to "0", "BM-CL源" to "1"))) }

    config(checkBoolean, fileDir)


    MaterialTheme {


        top(sourceSelect, sourceChoose)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                isClient(checkBoolean)
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
