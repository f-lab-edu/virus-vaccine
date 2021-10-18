package com.virusvaccine;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VirusVaccineApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(VirusVaccineApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void logTest(){
        logger.debug("this is debug, displayed on the log file.");
        logger.info("this is info, displayed on the console and log file.");
        logger.error("this is error, displayed on the console and log file.");
    }

}
