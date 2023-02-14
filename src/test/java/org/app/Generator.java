package org.app;

import org.app.entity.Municipality;
import org.app.entity.PartOfMunicipality;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return Math.abs(RAND.nextInt());
    }

    public static String randomCode() {
        return String.format("%06d", randomInt());
    }

    public static Municipality randomMunicipality() {
        Municipality municipality = new Municipality();
        municipality.setCode(randomCode());
        municipality.setName("name" + randomInt());
        return municipality;
    }

    public static List<Municipality> randomListMunicipalities() {
        List<Municipality> municipalities = new ArrayList<>();
        int count = 10;
        while (count > 0) {
            municipalities.add(randomMunicipality());
            count--;
        }
        return municipalities;
    }

    public static MunicipalityModel randomMunicipalityModel() {
        MunicipalityModel municipalityModel = new MunicipalityModel();
        municipalityModel.setCode(randomCode());
        municipalityModel.setName("name" + randomInt());
        return municipalityModel;
    }

    public static List<MunicipalityModel> randomListMunicipalityModel() {
        List<MunicipalityModel> municipalityModels = new ArrayList<>();
        int count = 10;
        while (count > 0) {
            municipalityModels.add(randomMunicipalityModel());
            count--;
        }
        return municipalityModels;
    }

    public static PartOfMunicipality randomPartOfMunicipality(Municipality municipality) {
        PartOfMunicipality partOfMunicipality = new PartOfMunicipality();
        partOfMunicipality.setName("name" + randomInt());
        partOfMunicipality.setMunicipality(municipality);
        partOfMunicipality.setCode(randomCode());
        return partOfMunicipality;
    }

    public static List<PartOfMunicipality> randomListPartOfMunicipalities(List<Municipality> municipalities) {
        List<PartOfMunicipality> partOfMunicipalities = new ArrayList<>();
        int count = 0;
        while (count < 10) {
            partOfMunicipalities.add(randomPartOfMunicipality(municipalities.size() > count ? municipalities.get(count) : null));
            count++;
        }
        return partOfMunicipalities;
    }

    public static PartOfMunicipalityModel randomPartOfMunicipalityModel(MunicipalityModel municipalityModel) {
        if (municipalityModel == null)
            return null;
        PartOfMunicipalityModel partOfMunicipalityModel = new PartOfMunicipalityModel();
        partOfMunicipalityModel.setName("name" + randomInt());
        partOfMunicipalityModel.setCode("code" + randomInt());
        partOfMunicipalityModel.setCodeMunicipality(municipalityModel.getCode());
        return partOfMunicipalityModel;
    }

    public static List<PartOfMunicipalityModel> randomListPartOfMunicipalityModels(List<MunicipalityModel> municipalityModels) {
        List<PartOfMunicipalityModel> partOfMunicipalityModels = new ArrayList<>();
        int count = 0;
        while (count < 10) {
            partOfMunicipalityModels.add(randomPartOfMunicipalityModel(municipalityModels.size() > count ? municipalityModels.get(count) : null));
            count++;
        }
        return partOfMunicipalityModels;
    }

    public static MunicipalityModel municipalityFromXMLFile(){
        return new MunicipalityModel("573060", "Kopidlno");
    }

    public static List<PartOfMunicipalityModel> partOfMunicipalityFromXMLFile(){
        List<PartOfMunicipalityModel> partOfMunicipalityModels = new ArrayList<>();
        partOfMunicipalityModels.add(new PartOfMunicipalityModel( "69299", "Kopidlno", "573060"));
        partOfMunicipalityModels.add(new PartOfMunicipalityModel( "69302", "Ledkov", "573060"));
        partOfMunicipalityModels.add(new PartOfMunicipalityModel( "97373", "Mlýnec", "573060"));
        partOfMunicipalityModels.add(new PartOfMunicipalityModel( "31801", "Drahoraz","573060"));
        partOfMunicipalityModels.add(new PartOfMunicipalityModel( "31828", "Pševes", "573060"));
        return partOfMunicipalityModels;
    }
}
