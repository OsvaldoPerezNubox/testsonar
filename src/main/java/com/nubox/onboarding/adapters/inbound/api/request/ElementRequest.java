package com.nubox.onboarding.adapters.inbound.api.request;

import com.nubox.onboarding.domain.model.ElementData;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class ElementRequest {

    @NotNull
    private Integer id;

    @NotEmpty
    private List<String> values;

    public ElementData toDomain() {
        ElementData elementData = new ElementData();
        elementData.setId(id);
        elementData.setValues(values);
        return elementData;
    }

}
