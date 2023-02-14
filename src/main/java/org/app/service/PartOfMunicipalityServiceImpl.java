package org.app.service;

import org.app.dao.PartOfMunicipalityDAO;
import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.service.interfaces.PartOfMunicipalityService;

import java.util.List;
import java.util.Objects;

public class PartOfMunicipalityServiceImpl implements PartOfMunicipalityService {

    private final PartOfMunicipalityDAO partOfMunicipalityDAO;

    public PartOfMunicipalityServiceImpl() {
        this.partOfMunicipalityDAO = new PartOfMunicipalityDAO();
    }

    @Override
    public void savePartOfMunicipality(List<PartOfMunicipality> partOfMunicipalities) {
        Objects.requireNonNull(partOfMunicipalities);
        partOfMunicipalityDAO.savePartOfMunicipality(partOfMunicipalities);
    }

    @Override
    public List<PartOfMunicipality> resetIdsMunicipalities(List<PartOfMunicipality> partOfMunicipalities,
                                                           List<Municipality> municipalities) {
        List<PartOfMunicipality> parts = List.copyOf(partOfMunicipalities);
        for (PartOfMunicipality part : parts) {
            for (Municipality municipality : municipalities) {
                if (part.getMunicipality().equals(municipality)) {
                    part.setMunicipality(municipality);
                }
            }
        }
        return parts;
    }

}