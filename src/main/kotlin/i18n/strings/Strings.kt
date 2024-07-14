package i18n.strings

import org.apache.commons.collections4.bidimap.TreeBidiMap


data class Strings(
    val sourcesTitle: String,
    val languageTitle: String,
    val languages: TreeBidiMap<String, String>,
    val sources: TreeBidiMap<String, String>,
    val downloadClientTitle: String,
    val downloadServerTitle: String,
)