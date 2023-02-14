package org.app.service.interfaces;

import org.app.entity.Municipality;

import java.util.List;

public interface MunicipalityService {

    /**
     * Save all Municipalities from the list to the DB
     *
     * @param municipalities to save to DB
     */
    void saveMunicipalities(List<Municipality> municipalities);

    /**
     * Gets all rows from table "municipalities"
     * Creates a list of municipalities from the given data
     *
     * @return all municipalities from DB
     */
    List<Municipality> getAllMunicipalities();

}
