package i18n.strings

import cafe.adriel.lyricist.LyricistStrings
import i18n.Locales
import org.apache.commons.collections4.bidimap.TreeBidiMap

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EN = Strings(
    sourcesTitle = "sources:",
    languageTitle = "language:",
    languages = TreeBidiMap(mapOf(
        "chinese language" to Locales.ZH,
        "english language" to Locales.EN,
    )),
    sources = TreeBidiMap(mapOf(
        "official sources" to "0",
        "bmcl sources" to "1",
    )),
    downloadClientTitle = "download client",
    downloadServerTitle = "download server"
)