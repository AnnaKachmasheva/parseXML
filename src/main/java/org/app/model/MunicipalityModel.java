package org.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.app.entity.AbstractClass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityModel extends AbstractClass {

    private String code;
    private String name;

}