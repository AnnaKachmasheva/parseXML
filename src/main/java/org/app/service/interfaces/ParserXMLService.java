package org.app.service.interfaces;

import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;

import java.util.List;
import java.util.zip.ZipInputStream;

public interface ParserXMLService {

    /**
     * Get zip data by URL. URL defined in class ParseConstatnt
     */
    void parse();

    /**
     * @param zipIn input stream for reading ZIP file
     */
    void parseZipInputStream(ZipInputStream zipIn);

    /**
     * Before calling method ¨parse¨ return an empty list
     * If parsing was unsuccessful, it will also return an empty list
     *
     * @return a list of objects Municipality
     */
    List<MunicipalityModel> getMunicipalityModels();

    /**
     * Before calling method ¨parse¨ return an empty list
     * If parsing was unsuccessful, it will also return an empty list
     *
     * @return a list of objects PartOfMunicipality
     */
    List<PartOfMunicipalityModel> getPartsOfMunicipalityModels();

}
