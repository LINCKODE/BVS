package me.linckode.buildversioningserver;

import me.linckode.buildversioningserver.config.Config;
import me.linckode.buildversioningserver.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static Config config;
    public static String version = "1.0.0";

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        logger.info("Starting BVS v." + version);

        config = ConfigManager.getConfig();

        logger.info("Starting spring:");

        SpringApplication.run(Main.class);
    }
}
