package org.app.mapper;

import org.app.Generator;
import org.app.entity.Municipality;
import org.app.model.MunicipalityModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipalityModel2MunicipalityMapperTest {

    private final MunicipalityModel2MunicipalityMapper mapper =
            new MunicipalityModel2MunicipalityMapper();

    @Test
    void toMunicipality_MunicipalityModel_Municipality() {
        MunicipalityModel municipalityModel = Generator.randomMunicipalityModel();

        Municipality municipality = mapper.toMunicipality(municipalityModel);

        assertEquals(municipalityModel.getCode(), municipality.getCode());
        assertEquals(municipalityModel.getName(), municipality.getName());
    }

    @Test
    void toMunicipality_Null_Null() {
        Municipality municipality = mapper.toMunicipality(null);

        assertNull(municipality);
    }

    @Test
    void toMunicipalityList_MunicipalityModelList_MunicipalityList() {
        List<MunicipalityModel> municipalityModelList = Generator.randomListMunicipalityModel();

        List<Municipality> municipalities = mapper.toMunicipalityList(municipalityModelList);

        assertEquals(municipalityModelList.size(), municipalities.size());
        for (int i = 0; i < municipalities.size(); i++) {
            assertEquals(municipalityModelList.get(i).getCode(), municipalities.get(i).getCode());
            assertEquals(municipalityModelList.get(i).getName(), municipalities.get(i).getName());
        }

    }
}