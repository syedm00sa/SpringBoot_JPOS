package com.bank.config;

import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.q2.Q2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JposConfig {
    private static final Logger logger = LoggerFactory.getLogger(JposConfig.class);

    @Value("${jpos.server.host}")
    private String host;

    @Value("${jpos.server.port}")
    private int port;

    @Bean
    public Q2 q2() {
        logger.info("Starting Q2 JPOS Server...");
        Q2 q2 = new Q2();
        try {
            q2.start();
            logger.info("Q2 JPOS Server started.");
        } catch (Exception e) {
            logger.error("Error starting Q2 JPOS Server", e);
        }
        return q2;
    }

    @Bean
    public ASCIIChannel asciiChannel() {
        return new ASCIIChannel(host, port, new ISO87APackager());
    }

}