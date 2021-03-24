package com.nubox.onboarding.domain.model.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FormElement {

    private Integer id;
    private String label;
    private String placeholder;
    private String type;
    private boolean required;
    private String requiredText;
    private String property;
    private Integer order;
    private List<FormOption> options;
}
