import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.lyricist.rememberStrings
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import i18n.Locales
import java.util.function.Consumer
import java.util.function.Supplier
import kotlin.io.path.Path
import kotlin.io.path.notExists
import kotlin.io.path.readText
import kotlin.io.path.writeText

data class God(
    @SerializedName("sourceHome") var sourceHome: String,
    @SerializedName("download-mode") var mode: String,
    @SerializedName("mc-version") var mcVersion: String,
    @SerializedName("last-select-file") var lastSelectFile: String,
    @SerializedName("language") var language: String,
)

val gson = GsonBuilder().setPrettyPrinting().create()
lateinit var config:God
val supplier: Supplier<God> = Supplier<God> {
    config = God("0", "0", "1.20.1", System.getProperty("user.dir"), Locales.EN)
    config
}
val configPath = Path("gugug.json")

@Composable
fun config(
    fileDir: MutableState<String>
) {
    if (configPath.notExists()) {
        configPath.writeText(gson.toJson(supplier.get()), Charsets.UTF_8)
    } else {
        config = gson.fromJson(configPath.readText(Charsets.UTF_8), God::class.java)
    }

    fileDir.value = config.lastSelectFile
}