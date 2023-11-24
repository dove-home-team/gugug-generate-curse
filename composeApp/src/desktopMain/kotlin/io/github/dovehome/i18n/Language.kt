package io.github.dovehome.i18n

import java.nio.file.Path
import java.nio.file.Paths

class Language(val userDir: Path = Paths.get("").toAbsolutePath()) {
    private val map: Map<String, String> = HashMap()
    fun setLanguage(i18nKey: String) {
    }
}