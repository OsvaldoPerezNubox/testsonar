package com.nubox.onboarding.domain.model.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StageConfig {

    @EqualsAndHashCode.Include
    private Integer id;
    private String title;
    private String description;
    private Integer order;
    private List<FormElement> formElements;
}
