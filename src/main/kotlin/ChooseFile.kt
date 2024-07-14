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
import kotlin.io.path.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

@Composable
fun chooseFile(fileDir: MutableState<String>) {
    val canSelectFile = remember { mutableStateOf(false) }
    Row {
        TextButton(
            onClick = {
                canSelectFile.value = true
            },
            enabled = true,
            modifier = Modifier.size(100.dp,40.dp)
        ) {
            Text("choose file")
        }
    }
    if (canSelectFile.value) {
        selectFile(canSelectFile, fileDir)
    }
}

@Composable
fun selectFile(canSelectFile: MutableState<Boolean>, fileDir: MutableState<String>) {
    var list by remember {
        val path = Path(config.lastSelectFile)
        mutableStateOf(if (path.isDirectory()) path.toFile().list() else path.parent.toFile().list())
    }
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

            Button(
                onClick = {
                    canSelectFile.value = false
                },
                modifier = Modifier.size(900.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
                Text("选取文件")
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {

                item {
                    TextButton(
                        onClick = {
                            val parent = Path(fileDir.value).parent
                            if (parent != null) {
                                fileDir.value = parent.toString()
                                config.lastSelectFile = fileDir.value
                                list = Path(config.lastSelectFile).toFile().list()
                                configPath.writeText(gson.toJson(config), Charsets.UTF_8)
                            }
                        },
                        enabled = true,
                        modifier = Modifier.size(900.dp,40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text("../")
                    }
                }
                items(list) {
                    TextButton(
                        onClick = {
                            val path = Path(fileDir.value)
                            val p = (if (path.isDirectory()) path else path.parent).resolve(it)
                            fileDir.value = p.toFile().toString()
                            if (p.isDirectory()) {
                                list = p.toFile().list()
                            }
                            config.lastSelectFile = fileDir.value
                            configPath.writeText(gson.toJson(config), Charsets.UTF_8)
                        },
                        enabled = true,
                        modifier = Modifier.size(900.dp,40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(it)
                    }
                }
            }

        }
    }
}