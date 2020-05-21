package com.example.data.serializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        for (format in DATE_FORMATS) {
            try {
                return SimpleDateFormat(format, Locale.US).parse(json?.asString)
            } catch (e: ParseException) {
            }

        }
        throw JsonParseException(
            "Unparseable date: \"" + json?.asString
                    + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS)
        )
    }

    companion object {
        private val DATE_FORMATS = arrayOf(
            "yyyy-MM-dd"
        )
    }
}
