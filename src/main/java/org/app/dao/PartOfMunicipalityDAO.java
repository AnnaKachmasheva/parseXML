package org.app.dao;

import lombok.extern.slf4j.Slf4j;
import org.app.constants.DBConstatnt;
import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PartOfMunicipalityDAO {

    private final Connection connection;
    private MunicipalityDAO municipalityDAO;

    public PartOfMunicipalityDAO() {
        connection = DBUtil.getConnection(DBConstatnt.RESOURCE_NAME);
    }

    public PartOfMunicipalityDAO(Connection connection) {
        this.connection = connection;
        this.municipalityDAO = new MunicipalityDAO(connection);
    }

    public void savePartOfMunicipality(List<PartOfMunicipality> partOfMunicipalities) {
        try (PreparedStatement statement = connection.prepareStatement(DBConstatnt.INSERT_PART_OF_MUNICIPALITY)) {
            for (PartOfMunicipality partOfMunicipalityModel : partOfMunicipalities) {
                statement.setString(1, partOfMunicipalityModel.getCode());
                statement.setString(2, partOfMunicipalityModel.getName());
                statement.setLong(3, partOfMunicipalityModel.getMunicipality().getId());
                statement.addBatch();
                statement.executeUpdate();
            }

            log.info("Save part of municipalities: {}.", partOfMunicipalities);
        } catch (SQLException e) {
            log.error("Save PartOfMunicipality SQL Exception : {}.", e.getMessage());
        }
    }

    public List<PartOfMunicipality> getAllPartOfMunicipalities() {
        List<PartOfMunicipality> partOfMunicipalities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(DBConstatnt.SELECT_ALL_PART_OF_MUNICIPALITY);
            while (result.next()) {
                PartOfMunicipality part = new PartOfMunicipality();
                part.setId(result.getLong("id"));
                part.setName(result.getString("part_name"));
                part.setCode(result.getString("part_code"));
                Municipality municipality = municipalityDAO.getMunicipalityById(result.getLong("id_municipality"));
                part.setMunicipality(municipality);
                partOfMunicipalities.add(part);
            }

            log.info("Find all parts of municipalities: {}.", partOfMunicipalities);
        } catch (SQLException e) {
            log.error("Get all PartOfMunicipalities SQL Exception: {}.", e.getMessage());
            e.printStackTrace();
        }
        return partOfMunicipalities;
    }

}
