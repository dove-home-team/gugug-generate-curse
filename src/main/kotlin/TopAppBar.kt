import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import i18n.Language
import org.apache.commons.collections4.bidimap.TreeBidiMap
import java.util.function.Function
import java.util.function.Supplier
import kotlin.io.path.writeText

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun dropdown(
    readOnly: Boolean,
    valueChangeEvent: (String) -> Unit = { },
    title: MutableState<String>,
    maps: MutableState<TreeBidiMap<String, String>>,
    superItemClick: Function<String, *> = Function {
        title.value = it
        config!!.sourceHome = maps.value.getValue(it)
        configPath.writeText(gson.toJson(config), Charsets.UTF_8)
        null
    },
    modifier: Modifier = Modifier.size(300.dp, 30.dp)
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },

    ) {
        TextField(
            readOnly = readOnly,
            value = title.value,
            onValueChange = valueChangeEvent,
            label = { Text(title.value) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = modifier
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            maps.value.forEach { (name, key) ->

                DropdownMenuItem(
                    onClick = {
                        superItemClick.apply(name)
                        expanded = false
                    }
                ) {
                    Text(name)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun top(sourceSelect: MutableState<TreeBidiMap<String, String>>, sourceChoose: MutableState<String>, lang: Language) {

    TopAppBar(
        modifier = Modifier.height(50.dp),
        backgroundColor = Color.Cyan
    ) {
        Text(
            text = lang.supplier.get().get("sources.title")!!,
            modifier = Modifier.size(70.dp, 30.dp)
        )
        dropdown(
            readOnly = true,
            title = sourceChoose,
            maps = sourceSelect
        )

    }
}