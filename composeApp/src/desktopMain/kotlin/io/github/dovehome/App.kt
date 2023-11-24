package io.github.dovehome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.text.style.TextAlign
import io.github.dovehome.Select.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.awt.FileDialog

//var option: EdgeOptions? = EdgeOptions().addArguments("headless")
//var driver: WebDriver? = EdgeDriver(option);

var driver: WebDriver = TODO();

enum class Select {
    EDGE,
    IE,
    FIREFOX,
    CHROME
}

fun selectBrowser(select: Select) {
    when(select) {
        EDGE -> EdgeDriver(EdgeOptions().addArguments("headless"))
        IE -> InternetExplorerDriver()
        FIREFOX -> FirefoxDriver(FirefoxOptions().addArguments("headless"))
        CHROME -> ChromeDriver(ChromeOptions().addArguments("headless"))
    }
}

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