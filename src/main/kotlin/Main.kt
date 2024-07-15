import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.lyricist.*

import i18n.strings.Strings


//全局添加compose注解
lateinit var lyricist: Lyricist<Strings>

@Composable
@Preview
fun App() {


    val fileDir: MutableState<String> = remember { mutableStateOf("choose file") }
    var firstRun by remember { mutableStateOf(true) }
    lyricist = rememberStrings()

    config(fileDir)
    if (firstRun) {
        lyricist.languageTag = config.language
        firstRun = false
    }


    MaterialTheme {


        top()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            chooseFile()
            download()

        }
    }

}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun SwitchLocaleButton(
    lyricist: Lyricist<Strings>,
    languageTag: LanguageTag,
    modifier: Modifier
) {
    Button(
        onClick = {
            lyricist.languageTag = languageTag
        },
        modifier = modifier,
    ) {
        Text(languageTag)
    }
}
