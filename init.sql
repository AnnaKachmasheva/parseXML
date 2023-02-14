DROP TABLE IF EXISTS municipality, part_of_municipality;

CREATE TABLE IF NOT EXISTS municipality (
                                    id serial PRIMARY KEY,
                                    municipality_code VARCHAR(255) NOT NULL,
                                    municipality_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS part_of_municipality (
                                        id serial PRIMARY KEY,
                                        part_code VARCHAR(255) NOT NULL,
                                        part_name VARCHAR(255) NOT NULL,
                                        id_municipality INT references municipality(id)
);