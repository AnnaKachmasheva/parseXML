package org.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PartOfMunicipality extends AbstractClass {

    private String code;
    private String name;
    private Municipality municipality;

    @Override
    public String toString() {
        return "PartOfMunicipality{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PartOfMunicipality partOfMunicipality = (PartOfMunicipality) o;
        if (!Objects.equals(name, partOfMunicipality.name))
            return false;
        return Objects.equals(code, partOfMunicipality.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
