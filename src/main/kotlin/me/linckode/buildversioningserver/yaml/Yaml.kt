package me.linckode.buildversioningserver.yaml

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import java.io.File

abstract class Yaml {

    fun getFromFile(file: File): Yaml {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.findAndRegisterModules()
        return mapper.readValue(file, this::class.java)
    }

    companion object {




        fun saveToFile(file: File, yamlObject: Yaml) {
            val mapper = ObjectMapper(YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            mapper.writeValue(file, yamlObject)
        }
    }
}