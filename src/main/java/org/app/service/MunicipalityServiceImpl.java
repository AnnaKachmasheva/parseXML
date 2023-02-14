package org.app.service;

import org.app.dao.MunicipalityDAO;
import org.app.entity.Municipality;
import org.app.service.interfaces.MunicipalityService;

import java.util.List;
import java.util.Objects;

public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityDAO municipalityDAO;

    public MunicipalityServiceImpl() {
        this.municipalityDAO = new MunicipalityDAO();
    }

    @Override
    public void saveMunicipalities(List<Municipality> municipalities) {
        Objects.requireNonNull(municipalities);
        municipalityDAO.saveMunicipalities(municipalities);
    }

    @Override
    public List<Municipality> getAllMunicipalities() {
        return municipalityDAO.getAllMunicipalities();
    }

}