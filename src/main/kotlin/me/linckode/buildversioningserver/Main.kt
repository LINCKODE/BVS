package me.linckode.buildversioningserver

import me.linckode.buildversioningserver.config.Config
import me.linckode.buildversioningserver.config.ConfigManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Main {

    companion object {
        var config: Config? = null

        const val version = "1.0.0"
    }

    val logger: Logger = LogManager.getLogger()

    fun main(args: Array<String>) {

        logger.info("Starting BVS v.$version")

        config = ConfigManager.getConfig()

        logger.info("Starting spring:")

        runApplication<Main>(*args)
    }

}

fun main(args: Array<String>) {
    Main().main(args)
}


