package org.app.service;

import lombok.extern.slf4j.Slf4j;
import org.app.constants.ParseConstatnt;
import org.app.model.MunicipalityModel;
import org.app.model.PartOfMunicipalityModel;
import org.app.service.interfaces.ParserXMLService;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class ParseXMLServiceImpl implements ParserXMLService {

    private final String URL;
    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    private List<MunicipalityModel> municipalityModels;
    private List<PartOfMunicipalityModel> partOfMunicipalityModels;


    public ParseXMLServiceImpl(String URL) {
        this.URL = URL;
        this.municipalityModels = new ArrayList<>();
        this.partOfMunicipalityModels = new ArrayList<>();
    }

    @Override
    public void parse() {
        HttpURLConnection connection = null;
        ZipInputStream zipIn;
        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            zipIn = new ZipInputStream(connection.getInputStream());

            ZipEntry entry = zipIn.getNextEntry();
            if ((entry != null) && !entry.isDirectory())
                parseZipInputStream(zipIn);
            zipIn.close();
        } catch (MalformedURLException mue) {
            log.error("Cannot download JSON from URL {}. Malformed URL.", URL);
        } catch (UnknownHostException uhe) {
            log.error("Cannot download JSON from URL {}. Unknown host.", URL);
        } catch (IOException e) {
            log.error("Cannot download JSON from URL {}.", URL);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    @Override
    public void parseZipInputStream(ZipInputStream zipIn) {
        MunicipalityModel municipalityModel = null;
        PartOfMunicipalityModel partOfMunicipalityModel = null;

        boolean isParseMunicipality = false;       // parsing of the municipality parsing goes
        boolean isParsePartOfMunicipality = false; // parsing of the part of municipality parsing goes
        boolean isPars = true;                     // whether to continue parsing the file. Parsed until the end of the <CastiObci>

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(zipIn);

            while (isPars && reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case ParseConstatnt.ELEMENT_MUNICIPALITY -> {
                            if (!isParsePartOfMunicipality)
                                municipalityModel = new MunicipalityModel();
                            isParseMunicipality = true;
                        }
                        case ParseConstatnt.ELEMENT_PART_OF_MUNICIPALITY -> {
                            partOfMunicipalityModel = new PartOfMunicipalityModel();
                            isParsePartOfMunicipality = true;
                        }
                        case ParseConstatnt.ELEMENT_CODE -> {
                            nextEvent = reader.nextEvent();
                            String code = nextEvent.asCharacters().getData();
                            String prefix = startElement.getName().getPrefix();
                            if (Objects.equals(prefix, ParseConstatnt.PREFIX_MUNICIPALITY) && partOfMunicipalityModel != null
                                    && isParseMunicipality && isParsePartOfMunicipality) {
                                partOfMunicipalityModel.setCodeMunicipality(code);
                            } else if (Objects.equals(prefix, ParseConstatnt.PREFIX_MUNICIPALITY) && municipalityModel != null) {
                                municipalityModel.setCode(code);
                            } else if (Objects.equals(prefix, ParseConstatnt.PREFIX_PART_OF_MUNICIPALITY) && partOfMunicipalityModel != null) {
                                partOfMunicipalityModel.setCode(code);
                            }
                        }
                        case ParseConstatnt.ELEMENT_NAME -> {
                            nextEvent = reader.nextEvent();
                            String name = nextEvent.asCharacters().getData();
                            String prefix = startElement.getName().getPrefix();
                            if (Objects.equals(prefix, ParseConstatnt.PREFIX_MUNICIPALITY) && municipalityModel != null) {
                                municipalityModel.setName(name);
                            } else if (Objects.equals(prefix, ParseConstatnt.PREFIX_PART_OF_MUNICIPALITY) && partOfMunicipalityModel != null) {
                                partOfMunicipalityModel.setName(name);
                            }
                        }
                    }
                }
                if (nextEvent.isEndElement()) {
                    switch (nextEvent.asEndElement().getName().getLocalPart()) {
                        case ParseConstatnt.ELEMENT_MUNICIPALITY -> {
                            isParseMunicipality = false;
                            if (municipalityModel != null && !isParsePartOfMunicipality) {
                                municipalityModels.add(municipalityModel);
                            }
                        }
                        case ParseConstatnt.ELEMENT_PART_OF_MUNICIPALITY -> {
                            if (partOfMunicipalityModel != null) {
                                partOfMunicipalityModels.add(partOfMunicipalityModel);
                            }
                            isParsePartOfMunicipality = false;
                        }
                        case ParseConstatnt.ELEMENT_PART_OF_MUNICIPALITIES -> isPars = false;
                    }
                }
            }
        } catch (XMLStreamException e) {
            log.error("XMLStreamException {}.", e.getMessage());
        }
    }

    @Override
    public List<MunicipalityModel> getMunicipalityModels() {
        return municipalityModels;
    }

    @Override
    public List<PartOfMunicipalityModel> getPartsOfMunicipalityModels() {
        return partOfMunicipalityModels;
    }

}