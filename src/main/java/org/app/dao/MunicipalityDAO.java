package org.app.dao;

import lombok.extern.slf4j.Slf4j;
import org.app.constants.DBConstatnt;
import org.app.entity.Municipality;
import org.app.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MunicipalityDAO {

    private final Connection connection;

    public MunicipalityDAO() {
        connection = DBUtil.getConnection(DBConstatnt.RESOURCE_NAME);
    }

    public MunicipalityDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveMunicipalities(List<Municipality> municipalities) {
        try {
            PreparedStatement statement = connection.prepareStatement(DBConstatnt.INSERT_MUNICIPALITY);
            for (Municipality municipality : municipalities) {
                statement.setString(1, municipality.getCode());
                statement.setString(2, municipality.getName());
                statement.executeUpdate();
            }
            statement.close();
            log.info("Saved municipalities: {}.", municipalities);
        } catch (SQLException e) {
            log.error("SQLException: {}.", e.getMessage());
        }
    }

    public List<Municipality> getAllMunicipalities() {
        List<Municipality> municipalities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(DBConstatnt.SELECT_ALL_MUNICIPALITIES);
            while (result.next()) {
                Municipality municipality = new Municipality();
                municipality.setId(result.getLong("id"));
                municipality.setName(result.getString("municipality_name"));
                municipality.setCode(result.getString("municipality_code"));
                municipalities.add(municipality);
            }
            statement.close();
            log.info("Find all municipalities: {}.", municipalities);
        } catch (SQLException e) {
            log.error("SQL Exception: {}.", e.getMessage());
        }
        return municipalities;
    }

    public Municipality getMunicipalityById(long idMunicipality) {
        Municipality municipality = null;
        try (PreparedStatement statement = connection.prepareStatement(DBConstatnt.GET_MUNICIPALITY_BY_ID)) {
            statement.setLong(1, idMunicipality);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                municipality = new Municipality();
                municipality.setId(result.getLong("id"));
                municipality.setName(result.getString("municipality_name"));
                municipality.setCode(result.getString("municipality_code"));
            }
            log.info("Find municipality: {}.", municipality);
        } catch (SQLException e) {
            log.error("SQL Exception: {}.", e.getMessage());
        }
        return municipality;
    }

}
