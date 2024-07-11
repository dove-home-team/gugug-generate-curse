import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.mrshiehx.cmcl.CMCL
import kotlin.io.path.*

@Composable
@Preview
fun App() {

    var expanded1 by remember { mutableStateOf(false) }
    var fileDir by remember { mutableStateOf("choose file") }
    var canSelectFile by remember { mutableStateOf(false) }
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            selectSources()
            Row {
                TextButton(
                    onClick = {
                          canSelectFile = true
                    },
                    enabled = true,
                    modifier = Modifier.size(100.dp,40.dp),

                ) {
                    Text(fileDir)
                }
            }
            Row {
                Button(    onClick = {
                    CMCL.main(arrayOf("install", "-h"))
                }) {
                    Text("下载")
                }
            }
        }

    }
    if (canSelectFile) {
        selectFile()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun selectSources(): String {
    var expanded by remember { mutableStateOf(false) }
    val sourceSelect: MutableMap<String, String> =  remember { mutableMapOf("官方源" to "0", "BM-CL源" to "1") }
    var selectText by remember { mutableStateOf("官方源") }
    Row {
        Text("下载源")
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectText,
                onValueChange = { },
                label = { Text(selectText) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.size(300.dp, 30.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                sourceSelect.forEach { (name, key) ->

                    DropdownMenuItem(
                        onClick = {
                            selectText = name
                            expanded = false
                        }
                    ) {
                        Text(name)
                    }
                }
            }
        }
    }
    return sourceSelect[selectText]!!
}

@Composable
fun selectFile() {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
