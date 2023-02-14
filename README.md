# Parser XML
The application downloads data in xml format from the URL (*https://www.smartform.cz/download/kopidlno.xml.zip*), processes it and saves it to the PostgreSQL database.

Data is stored in 2 tables:

- **municipality** (*code, name*)
- **part_of_municipality** (*code, name, id*
municipality to which part of the municipality belongs)

Tables in the DB can be created by running script 'init.sql'.
