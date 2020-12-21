package me.linckode.buildversioningserver.config

import me.linckode.buildversioningserver.yaml.Yaml
import org.apache.logging.log4j.LogManager
import java.io.File
import kotlin.system.exitProcess

object ConfigManager {


    @JvmStatic
    private val logger = LogManager.getLogger()

    @JvmStatic
    fun getConfig(): Config{

        val appsFolder = File("apps/")
        if (!appsFolder.exists())
            appsFolder.mkdir()

        val userFolder = File("users/")
        if (!userFolder.exists())
            userFolder.mkdir()

        logger.info("Loading config...")

        val configFile = File("config.yaml")

        if (!configFile.exists()){
            logger.warn("Couldn't find config file!")
            logger.info("Creating config...")

            val config = Config("REPLACE-ME")
            Yaml.saveToFile(configFile, config)
            logger.info("Created config file at ${configFile.absolutePath}")
            logger.info("Set up a master token the restart the server.")
            logger.info("Shutting down!")
            exitProcess(0)
        }

        var config = Config()
        config = config.getFromFile(configFile) as Config

        if (config.masterToken == "REPLACE-ME" || config.masterToken == ""){
            logger.warn("Master token is not set!")
            logger.info("Set up a master token the restart the server.")
            logger.info("Shutting down!")
            exitProcess(0)
        }

        logger.info("Master token is [>${config.masterToken}<]")

        return config

    }


}