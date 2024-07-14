import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import org.apache.commons.collections4.bidimap.TreeBidiMap
import java.util.function.Function
import kotlin.io.path.bufferedWriter
import kotlin.io.path.writeText

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun dropdown(
    readOnly: Boolean,
    valueChangeEvent: (String) -> Unit = { },
    title: MutableState<String>,
    maps: TreeBidiMap<String, String>,
    superItemClick: Function<String, *>,
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
            maps.forEach { (name, _) ->

                DropdownMenuItem(
                    onClick = {
                        superItemClick.apply(name)
                        configPath.writeText(gson.toJson(config), Charsets.UTF_8)
                        expanded = false
                    }
                ) {
                    Text(name)
                }
            }
        }
    }
}

@Composable
fun top() {
    TopAppBar(
        modifier = Modifier.height(50.dp),
        backgroundColor = Color.Cyan
    ) {



        ProvideStrings(lyricist) {
            val sourcesTitle = LocalStrings.current.sourcesTitle
            val sources = LocalStrings.current.sources
            val titleSources = mutableStateOf(sourcesTitle + sources.getKey(config.sourceHome))
            dropdown(
                readOnly = true,
                title = titleSources,
                maps = sources,
                superItemClick = {
                    {
                        config.sourceHome = sources.getValue(it)
                        null
                    }
                }
            )
        }

        ProvideStrings(lyricist) {
            val languageTitle = LocalStrings.current.languageTitle
            val languages = LocalStrings.current.languages
            val titleLanguage = mutableStateOf(languageTitle + languages.getKey(config.language))
            dropdown(
                readOnly = true,
                title = titleLanguage,
                maps = languages,
                superItemClick = {

                    config.language = languages.getValue(it)
                    lyricist.languageTag = config.language

                    null
                }
            )
        }



    }




}