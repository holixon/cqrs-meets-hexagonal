package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.*

@Converter
class StringListConverter : AttributeConverter<List<String?>?, String?> {
    override fun convertToDatabaseColumn(stringList: List<String?>?): String? {
        return if (stringList != null) java.lang.String.join(SPLIT_CHAR, stringList) else ""
    }

    override fun convertToEntityAttribute(string: String?): List<String?>? {
        return if (string != null) Arrays.asList(*string.split(SPLIT_CHAR.toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()) else emptyList<String>()
    }

    companion object {
        private const val SPLIT_CHAR = ";"
    }
}