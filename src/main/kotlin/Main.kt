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
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.mrshiehx.cmcl.CMCL
import kotlin.io.path.*

data class GuGuG(
    @SerializedName("mc-version") val mcVersion: String,
)

val GSON = GsonBuilder().setPrettyPrinting().create()
val path = Path("gugug.json")
var guGuG: GuGuG? = null

fun configInit() {
    if (path.notExists()) {
        guGuG = GuGuG("1.20.1")
        path.writeText(GSON.toJson(guGuG), Charsets.UTF_8)


    } else {
        guGuG = GSON.fromJson(path.readText(Charsets.UTF_8), GuGuG::class.java)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("0", "1", "2")
    val sourceSelect=  remember { mutableMapOf("0" to "官方源", "1" to "BM-CL源") }
    var selectText by remember { mutableStateOf(sourceSelect["0"]!!) }
    configInit()
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        sourceSelect.forEach { (key, name) ->

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
            Row {
                Button(    onClick = {
                    CMCL.main(arrayOf("install", "-h"))
                }) {
                    Text("下载")
                }
            }
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
