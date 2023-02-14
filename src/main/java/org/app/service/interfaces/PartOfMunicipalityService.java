package org.app.service.interfaces;

import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;

import java.util.List;

public interface PartOfMunicipalityService {

    /**
     * Save all PartOfMunicipalities from the list to the DB
     *
     * @param partOfMunicipalities to save to DB
     */
    void savePartOfMunicipality(List<PartOfMunicipality> partOfMunicipalities);

    /**
     * Changes partOfMunicipality's municipality to the object from list municipalities
     * Comparison for compliance of the municipality is carried out by name and code
     *
     * @param partOfMunicipalities list with data to update
     * @param municipalities       a list with objects that will be used for updating partOfMunicipalities
     * @return list of PartOfMunicipalities with updated municipalities
     */
    List<PartOfMunicipality> resetIdsMunicipalities(List<PartOfMunicipality> partOfMunicipalities, List<Municipality> municipalities);

}
