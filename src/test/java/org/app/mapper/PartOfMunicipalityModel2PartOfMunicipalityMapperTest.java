package org.app.mapper;

import org.app.Generator;
import org.app.entity.PartOfMunicipality;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PartOfMunicipalityModel2PartOfMunicipalityMapperTest {

    private final PartOfMunicipalityModel2PartOfMunicipalityMapper mapper =
            new PartOfMunicipalityModel2PartOfMunicipalityMapper();

    @Test
    void toPartOfMunicipality_PartOfMunicipalityModel_PartOfMunicipality() {
        MunicipalityModel municipalityModel = Generator.randomMunicipalityModel();
        PartOfMunicipalityModel partOfMunicipalityModel = Generator.randomPartOfMunicipalityModel(municipalityModel);

        PartOfMunicipality partOfMunicipality = mapper.toPartOfMunicipality(partOfMunicipalityModel, List.of(municipalityModel));

        assertEquals(partOfMunicipalityModel.getName(), partOfMunicipality.getName());
        assertEquals(partOfMunicipalityModel.getCode(), partOfMunicipality.getCode());
        assertEquals(municipalityModel.getName(), partOfMunicipality.getMunicipality().getName());
        assertEquals(municipalityModel.getCode(), partOfMunicipality.getMunicipality().getCode());
    }

    @Test
    void toPartOfMunicipality_Null_Null() {
        PartOfMunicipality partOfMunicipality = mapper.toPartOfMunicipality(null, List.of(Generator.randomMunicipalityModel()));

        assertNull(partOfMunicipality);
    }

    @Test
    void toPartOfMunicipalityList_PartOfMunicipalityModelList_PartOfMunicipalityList() {
        List<MunicipalityModel> municipalityModelList = Generator.randomListMunicipalityModel();
        List<PartOfMunicipalityModel> partOfMunicipalityModels = Generator.randomListPartOfMunicipalityModels(municipalityModelList);

        List<PartOfMunicipality> partOfMunicipalities = mapper.toPartOfMunicipalityList(partOfMunicipalityModels, municipalityModelList);

        assertEquals(partOfMunicipalityModels.size(), partOfMunicipalities.size());
        for (int i = 0; i < partOfMunicipalities.size(); i++) {
            assertEquals(partOfMunicipalityModels.get(i).getName(), partOfMunicipalities.get(i).getName());
            assertEquals(partOfMunicipalityModels.get(i).getCode(), partOfMunicipalities.get(i).getCode());
            assertEquals(municipalityModelList.get(i).getName(), partOfMunicipalities.get(i).getMunicipality().getName());
            assertEquals(municipalityModelList.get(i).getCode(), partOfMunicipalities.get(i).getMunicipality().getCode());
        }
    }
}