package i18n.strings

import cafe.adriel.lyricist.LyricistStrings
import i18n.Locales
import org.apache.commons.collections4.bidimap.TreeBidiMap

@LyricistStrings(languageTag = Locales.ZH)
internal val ZH = Strings(
    sourcesTitle = "下载源：",
    languageTitle = "语言：",
    languages = TreeBidiMap(mapOf(
        "中文" to Locales.ZH,
        "英文" to Locales.EN,
    )),
    sources = TreeBidiMap(mapOf(
        "官方源" to "0",
        "bmcl源" to "1",
    )),
    downloadClientTitle = "下载客户端",
    downloadServerTitle = "下载服务端",
)