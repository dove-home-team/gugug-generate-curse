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
    chooseTitle = "已选取文件：",
    noChooseTitle = "请选取文件",
    modes = TreeBidiMap(mapOf(
        "鸽子之家csv" to "0",
        "curse包下载" to "1",
        "modrinth包下载" to "2",
    )),
    modeTitle = "下载模式：",
)