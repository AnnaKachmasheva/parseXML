package org.app.service.interfaces;

import org.app.Generator;
import org.app.constants.ParseConstatnt;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;
import org.app.service.ParseXMLServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserXMLServiceTest {

    private ParserXMLService parserXMLService;

    @BeforeEach
    public void init() {
        parserXMLService = new ParseXMLServiceImpl(ParseConstatnt.XML_URL);
    }

    @Test
    void parse_existFile_success() {
        List<PartOfMunicipalityModel> expectedPartOfMunicipalities = Generator.partOfMunicipalityFromXMLFile();
        MunicipalityModel expectedMunicipality = Generator.municipalityFromXMLFile();

        parserXMLService.parse();

        List<MunicipalityModel> municipalities = parserXMLService.getMunicipalityModels();
        List<PartOfMunicipalityModel> partOfMunicipalities = parserXMLService.getPartsOfMunicipalityModels();

        assertEquals(1, municipalities.size());
        assertEquals(expectedMunicipality.getName(), municipalities.get(0).getName());
        assertEquals(expectedMunicipality.getCode(), municipalities.get(0).getCode());

        assertEquals(5, partOfMunicipalities.size());
        for (int i = 0; i < expectedPartOfMunicipalities.size(); i++) {
            assertEquals(expectedPartOfMunicipalities.get(i).getName(), partOfMunicipalities.get(i).getName());
            assertEquals(expectedPartOfMunicipalities.get(i).getCode(), partOfMunicipalities.get(i).getCode());
            assertEquals(expectedPartOfMunicipalities.get(i).getCodeMunicipality(), partOfMunicipalities.get(i).getCodeMunicipality());
        }
    }

}