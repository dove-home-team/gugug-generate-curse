package io.github.dovehome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skia.paragraph.TextBox
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import java.awt.FileDialog

//var option: EdgeOptions? = EdgeOptions().addArguments("headless")
//var driver: WebDriver? = EdgeDriver(option);


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {

        var greetingText by remember { mutableStateOf("Test!") }
        var showImage by remember { mutableStateOf(false) }
        var fileText by remember { mutableStateOf("") }
        val selectButton by remember { mutableStateOf("select file") }
        var checkVersion by remember { mutableStateOf(true) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Checkbox(checked = checkVersion, onCheckedChange = {
                    checkVersion = checkVersion.not()
                })
            }
            Text(
                if (fileText.isNotEmpty()) fileText else "please select file",
                textAlign = TextAlign.Center,
                softWrap = true
            )
            Button(onClick = {
                val fileDialog = FileDialog(ComposeWindow())
                fileDialog.isVisible = true
                val directory = fileDialog.directory
                val file = fileDialog.file
                if (directory != null && file != null)
                    fileText = "$directory$file"
            }) {
                Text(
                    selectButton,
                    softWrap = false
                )
            }
            Button(onClick = {
                greetingText = "Compose: ${Greeting().greet()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                Image(
                    painterResource("compose-multiplatform.xml"),
                    null
                )
            }
        }
    }
}