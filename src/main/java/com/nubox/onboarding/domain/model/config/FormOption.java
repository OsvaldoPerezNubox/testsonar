package com.nubox.onboarding.domain.model.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormOption {

    private Integer id;
    private String label;
    private String value;
    private String imgUrl;
    private Integer order;
    private FormElement conditionalElement;
}
