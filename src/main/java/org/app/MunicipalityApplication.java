package org.app;

import org.app.constants.ParseConstatnt;
import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.mapper.MunicipalityModel2MunicipalityMapper;
import org.app.mapper.PartOfMunicipalityModel2PartOfMunicipalityMapper;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;
import org.app.service.MunicipalityServiceImpl;
import org.app.service.ParseXMLServiceImpl;
import org.app.service.PartOfMunicipalityServiceImpl;
import org.app.service.interfaces.MunicipalityService;
import org.app.service.interfaces.ParserXMLService;
import org.app.service.interfaces.PartOfMunicipalityService;
import org.app.utils.DBUtil;

import java.util.List;

public class MunicipalityApplication {

    public static void main(String[] args) {

        // Parse data from xml file by URL
        ParserXMLService parserXMLService = new ParseXMLServiceImpl(ParseConstatnt.XML_URL);
        parserXMLService.parse();

        // Save parsed data
        saveData(parserXMLService.getMunicipalityModels(), parserXMLService.getPartsOfMunicipalityModels());
    }

    /**
     * save data to DB
     *
     * @param municipalityModels       list of parsed MunicipalityModel
     * @param partOfMunicipalityModels list of parsed PartOfMunicipalityModel
     */
    private static void saveData(List<MunicipalityModel> municipalityModels,
                                 List<PartOfMunicipalityModel> partOfMunicipalityModels) {

        // Mapped data from models
        MunicipalityModel2MunicipalityMapper municipalityModel2MunicipalityMapper =
                new MunicipalityModel2MunicipalityMapper();
        PartOfMunicipalityModel2PartOfMunicipalityMapper partOfMunicipalityModel2PartOfMunicipalityMapper =
                new PartOfMunicipalityModel2PartOfMunicipalityMapper();
        List<Municipality> municipalities = municipalityModel2MunicipalityMapper.toMunicipalityList(municipalityModels);
        List<PartOfMunicipality> partOfMunicipalities = partOfMunicipalityModel2PartOfMunicipalityMapper
                .toPartOfMunicipalityList(partOfMunicipalityModels, municipalityModels);

        MunicipalityService municipalityService = new MunicipalityServiceImpl();

        // Save Municipalities to DB
        municipalityService.saveMunicipalities(municipalities);

        // Get all saved municipalities from DB
        List<Municipality> savedMunicipalities = municipalityService.getAllMunicipalities();

        PartOfMunicipalityService partOfMunicipalityService = new PartOfMunicipalityServiceImpl();

        // Reset the IDs of all municipalities in the Parts of the municipalities
        List<PartOfMunicipality> updatedPartOfMunicipalities = partOfMunicipalityService.
                resetIdsMunicipalities(partOfMunicipalities, savedMunicipalities);

        // Save part of municipalities to DB
        partOfMunicipalityService.savePartOfMunicipality(updatedPartOfMunicipalities);

        DBUtil.closeConnection();
    }

}