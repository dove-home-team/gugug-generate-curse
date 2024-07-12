package i18n

import org.apache.commons.collections4.bidimap.TreeBidiMap
import java.util.function.Supplier

enum class Language
(val supplier: Supplier<TreeBidiMap<String, String>>) {

    EnUs(Supplier<TreeBidiMap<String, String>> {
        TreeBidiMap(mapOf(

            "sources.0" to "official sources",
            "sources.1" to "BM-CL sources",
            "sources.title" to "sources:",
            "client.title" to "download client mode"
        ))

    }),
    ZhCn(Supplier<TreeBidiMap<String, String>> {
        TreeBidiMap(mapOf(
            "sources.0" to "官方源",
            "sources.1" to "BM-CL源",
            "sources.title" to "下载源:",
            "client.title" to "下载客户端模式"
        ))

    }),
    ;
}