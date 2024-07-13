import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.io.path.writeText


@Composable
fun isClient(checkBoolean: MutableState<Boolean>) {
    Text(lyricist.strings.clientTitle)

    Checkbox(
        checked = checkBoolean.value,
        onCheckedChange = {
            checkBoolean.value = checkBoolean.value.not()
            config.client = checkBoolean.value
            configPath.writeText(gson.toJson(config), Charsets.UTF_8)
        },
        modifier = Modifier.size(30.dp, 30.dp)
    )
}