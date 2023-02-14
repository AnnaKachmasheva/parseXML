package org.app.mapper;

import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartOfMunicipalityModel2PartOfMunicipalityMapper {

    public PartOfMunicipality toPartOfMunicipality(PartOfMunicipalityModel partOfMunicipalityModel,
                                                   List<MunicipalityModel> municipalityModels) {
        if (partOfMunicipalityModel == null)
            return null;
        PartOfMunicipality partOfMunicipality = new PartOfMunicipality();
        partOfMunicipality.setName(partOfMunicipalityModel.getName());
        partOfMunicipality.setCode(partOfMunicipalityModel.getCode());
        Municipality municipality = new Municipality();
        municipality.setCode(partOfMunicipalityModel.getCodeMunicipality());
        if (municipalityModels != null) {
            String modelName = municipalityModels.stream()
                    .filter(m -> Objects.equals(m.getCode(), partOfMunicipalityModel.getCodeMunicipality()))
                    .findFirst()
                    .map(MunicipalityModel::getName)
                    .orElse(null);
            municipality.setName(modelName);
            partOfMunicipality.setMunicipality(municipality);
        }
        return partOfMunicipality;
    }

    public List<PartOfMunicipality> toPartOfMunicipalityList(List<PartOfMunicipalityModel> partOfMunicipalityModels,
                                                             List<MunicipalityModel> municipalityModels) {
        List<PartOfMunicipality> partOfMunicipalities = new ArrayList<>(partOfMunicipalityModels.size());
        for (PartOfMunicipalityModel partOfMunicipalityModel : partOfMunicipalityModels) {
            partOfMunicipalities.add(toPartOfMunicipality(partOfMunicipalityModel, municipalityModels));
        }
        return partOfMunicipalities;
    }

}
