package org.app.dao;

import org.app.Constants;
import org.app.Generator;
import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.utils.DBUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PartOfMunicipalityDAOTest {

    private static PartOfMunicipalityDAO partOfMunicipalityDAO;
    private static List<Municipality> savedMunicipalities;

    @BeforeAll
    static void init() {
        Connection connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
        try {
            connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
            connection.setAutoCommit(false);  // any operation in this test will be discarded
        } catch (SQLException e) {
            e.printStackTrace();
        }
        partOfMunicipalityDAO = new PartOfMunicipalityDAO(connection);
    }

    @BeforeEach
    void clearAllTables() {
        Connection connection = DBUtil.getConnection(Constants.RESOURCE_NAME);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.TRUNCATE_TABLES);
            preparedStatement.executeUpdate();

            MunicipalityDAO municipalityDAO = new MunicipalityDAO(connection);
            List<Municipality> municipalities = Generator.randomListMunicipalities();
            municipalityDAO.saveMunicipalities(municipalities);
            savedMunicipalities = municipalityDAO.getAllMunicipalities();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void savePartOfMunicipality_successful() {
        List<PartOfMunicipality> partOfMunicipalities = Generator.randomListPartOfMunicipalities(savedMunicipalities);

        partOfMunicipalityDAO.savePartOfMunicipality(partOfMunicipalities);
        List<PartOfMunicipality> partsFromDB = partOfMunicipalityDAO.getAllPartOfMunicipalities();

        assertEquals(partOfMunicipalities.size(), partsFromDB.size());
        for (int i = 0; i < partsFromDB.size(); i++) {
            assertNotNull(partsFromDB.get(i).getId());
            assertEquals(partOfMunicipalities.get(i).getCode(), partsFromDB.get(i).getCode());
            assertEquals(partOfMunicipalities.get(i).getName(), partsFromDB.get(i).getName());
            assertEquals(partOfMunicipalities.get(i).getMunicipality().getId(), partsFromDB.get(i).getMunicipality().getId());
            assertEquals(partOfMunicipalities.get(i).getMunicipality().getName(), partsFromDB.get(i).getMunicipality().getName());
            assertEquals(partOfMunicipalities.get(i).getMunicipality().getCode(), partsFromDB.get(i).getMunicipality().getCode());
        }
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