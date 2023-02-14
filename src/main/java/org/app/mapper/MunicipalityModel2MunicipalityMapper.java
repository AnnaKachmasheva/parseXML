package org.app.mapper;

import org.app.model.MunicipalityModel;

import java.util.ArrayList;
import java.util.List;

public class MunicipalityModel2MunicipalityMapper {

    public org.app.entity.Municipality toMunicipality(MunicipalityModel municipalityModel) {
        if (municipalityModel == null)
            return null;
        org.app.entity.Municipality municipality = new org.app.entity.Municipality();
        municipality.setName(municipalityModel.getName());
        municipality.setCode(municipalityModel.getCode());
        return municipality;
    }

    public List<org.app.entity.Municipality> toMunicipalityList(List<MunicipalityModel> municipalityModels) {
        List<org.app.entity.Municipality> municipalities = new ArrayList<>(municipalityModels.size());
        for (MunicipalityModel municipalityModel : municipalityModels) {
            municipalities.add(toMunicipality(municipalityModel));
        }
        return municipalities;
    }

}
