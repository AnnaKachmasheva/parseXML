package org.app.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class Municipality extends AbstractClass {

    private String code;
    private String name;

    @Override
    public String toString() {
        return "Municipality{" +
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
        Municipality municipality = (Municipality) o;
        if (!Objects.equals(name, municipality.name))
            return false;
        return Objects.equals(code, municipality.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}