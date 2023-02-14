package org.app.dao;

import org.app.Constants;
import org.app.Generator;
import org.app.entity.Municipality;
import org.app.utils.DBUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipalityDAOTest {

    private static MunicipalityDAO municipalityDAO;

    @BeforeAll
    static void init() {
        Connection connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
        try {
            connection.setAutoCommit(false);  // any operation in this test will be discarded
        } catch (SQLException e) {
            e.printStackTrace();
        }
        municipalityDAO = new MunicipalityDAO(connection);
    }

    @BeforeEach
    void clearAllTables() {
        Connection connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.TRUNCATE_TABLES);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveMunicipalities_successful() {
        List<Municipality> municipalities = Generator.randomListMunicipalities();

        municipalityDAO.saveMunicipalities(municipalities);
        List<Municipality> municipalitiesFromDB = municipalityDAO.getAllMunicipalities();

        assertEquals(municipalities.size(), municipalitiesFromDB.size());
        for (int i = 0; i < municipalitiesFromDB.size(); i++) {
            assertNotNull(municipalitiesFromDB.get(i).getId());
            assertEquals(municipalities.get(i).getCode(), municipalitiesFromDB.get(i).getCode());
            assertEquals(municipalities.get(i).getName(), municipalitiesFromDB.get(i).getName());
        }
    }

    @Test
    void getMunicipalityById_NonExistentId_Null() {
        Municipality municipality = municipalityDAO.getMunicipalityById(Generator.randomInt() + 100);
        assertNull(municipality);
    }

    @Test
    void getMunicipalityById_ExistentId_Municipality() {
        Municipality municipality = Generator.randomMunicipality();
        municipalityDAO.saveMunicipalities(List.of(municipality));

        List<Municipality> municipalities = municipalityDAO.getAllMunicipalities();
        assertEquals(1, municipalities.size());
        Long idMunicipality = municipalities.get(0).getId();
        Municipality municipalityResult = municipalityDAO.getMunicipalityById(idMunicipality);

        assertEquals(idMunicipality, municipalityResult.getId());
        assertEquals(municipality.getCode(), municipalityResult.getCode());
        assertEquals(municipality.getName(), municipalityResult.getName());
    }

    @AfterAll
    static void teardown() {
        Connection connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}