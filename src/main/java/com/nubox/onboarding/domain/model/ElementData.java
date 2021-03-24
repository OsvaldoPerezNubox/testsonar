package com.nubox.onboarding.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ElementData {

    Integer id;
    String label;
    List<String> values;
}
