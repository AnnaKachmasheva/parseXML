package org.app.utils;

import org.app.constants.DBConstatnt;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void getPropertyTest() {
        String driver = DBUtil.getProperty(DBConstatnt.RESOURCE_NAME, "driver");
        String user = DBUtil.getProperty(DBConstatnt.RESOURCE_NAME, "user");
        assertEquals("org.postgresql.Driver", driver);
        assertEquals("postgres", user);
    }

    @Test
    void getConnectionTest() {
        Connection dbConnection = DBUtil.getConnection(DBConstatnt.RESOURCE_NAME);
        assertNotNull(dbConnection, "Connection is successfull");
    }

}