package org.app.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class DBUtil {

    private static Connection connection = null;
    private static Properties properties = null;

    public static String getProperty(String resourceName, String key) {
        if (properties != null) {
            return properties.getProperty(key);
        } else {
            Properties prop = new Properties();
            InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream(resourceName);
            try {
                prop.load(inputStream);
                return prop.getProperty(key);
            } catch (IOException e) {
                log.error("Get property DBUtils. IOException: {}.", e.getMessage());
            }
        }
        return null;
    }

    public static Connection getConnection(String resourceName) {
        if (connection != null)
            return connection;

        try {
            String driver = DBUtil.getProperty(resourceName, "driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(
                    Objects.requireNonNull(getProperty(resourceName, "url")),
                    getProperty(resourceName, "user"),
                    getProperty(resourceName, "password"));
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            log.error("Get connection. ClassNotFoundException: {}.", e.getMessage());
        } catch (SQLException e) {
            log.error("Get connection. SQLException: {}.", e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error("Close connection. SQLException: {}.", e.getMessage());
        }
    }

}