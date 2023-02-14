package org.app.constants;

public final class DBConstatnt {

    public static final String RESOURCE_NAME = "database.properties";

    public static final String INSERT_MUNICIPALITY = "INSERT INTO public.municipality(municipality_code, municipality_name) VALUES(?, ?)";
    public static final String SELECT_ALL_MUNICIPALITIES = "SELECT * FROM public.municipality";
    public static final String SELECT_ALL_PART_OF_MUNICIPALITY = "SELECT * FROM public.part_of_municipality";

    public static final String GET_MUNICIPALITY_BY_ID = "SELECT * FROM public.municipality WHERE id=?";

    public static final String INSERT_PART_OF_MUNICIPALITY = "INSERT INTO public.part_of_municipality" +
            "(part_code, part_name, id_municipality) VALUES(?, ?, ?)";

}
