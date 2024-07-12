import androidx.compose.runtime.MutableState
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import i18n.Language
import org.apache.commons.collections4.bidimap.TreeBidiMap
import java.util.function.Supplier
import kotlin.io.path.Path
import kotlin.io.path.notExists
import kotlin.io.path.readText
import kotlin.io.path.writeText

data class God(
    @SerializedName("sourceHome") var sourceHome: String,
    @SerializedName("download-mode") var mode: String,
    @SerializedName("is-client") var client: Boolean,
    @SerializedName("mc-version") var mcVersion: String,
    @SerializedName("last-select-file") var lastSelectFile: String,
    @SerializedName("language") var language: String,
)

val gson = GsonBuilder().setPrettyPrinting().create()
var config:God? = null
val supplier: Supplier<God> = Supplier<God> {
    config = God("0", "0", true, "1.20.1", System.getProperty("user.dir"), Language.EnUs.name)
    config
}
val configPath = Path("gugug.json")

fun config(
    sourceChoose: MutableState<String>,
    checkBoolean: MutableState<Boolean>,
    fileDir: MutableState<String>,
    language: MutableState<String>
) {
    if (configPath.notExists()) {
        configPath.writeText(gson.toJson(supplier.get()), Charsets.UTF_8)
    } else {
        config = gson.fromJson(configPath.readText(Charsets.UTF_8), God::class.java)
    }

    checkBoolean.value = config!!.client
    fileDir.value = config!!.lastSelectFile
    language.value = config!!.language

}