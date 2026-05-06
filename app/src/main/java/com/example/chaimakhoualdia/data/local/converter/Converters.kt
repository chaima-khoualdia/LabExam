package com.example.chaimakhoualdia.data.local.converter

/**
 * Simple converters for internal use (no Room annotations in the in-memory implementation).
 */
class Converters {
    private val separator = "||"

    fun fromStringList(list: List<String>?): String = list?.joinToString(separator) ?: ""

    fun toStringList(data: String?): List<String> = data?.takeIf { it.isNotBlank() }
        ?.split(separator) ?: emptyList()
}


