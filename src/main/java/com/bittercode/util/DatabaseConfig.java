package com.bittercode.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private static final String CONFIG_PATH = "/app/config.properties";

    public static String DRIVER_NAME;
    public static String CONNECTION_STRING;
    public static String DB_USER_NAME;
    public static String DB_PASSWORD;

    static {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            props.load(fis);

            DRIVER_NAME = props.getProperty("db.driver");
            CONNECTION_STRING = props.getProperty("db.url");
            DB_USER_NAME = props.getProperty("db.username");
            DB_PASSWORD = props.getProperty("db.password");

            System.out.println("Loaded DB config: " + CONNECTION_STRING + ", user=" + DB_USER_NAME);

            if (CONNECTION_STRING == null || DB_USER_NAME == null || DB_PASSWORD == null) {
                throw new RuntimeException("Missing DB config in " + CONFIG_PATH);
            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config from " + CONFIG_PATH, e);
        }
    }
}
