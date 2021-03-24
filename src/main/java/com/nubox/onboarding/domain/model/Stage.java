package com.nubox.onboarding.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Stage {

    private Integer id;
    private List<ElementData> elements = new ArrayList<>();

    public Stage(Integer id) {
        this.id = id;
    }

    public void addElement(ElementData elementData) {
        elements.add(elementData);
    }

}
