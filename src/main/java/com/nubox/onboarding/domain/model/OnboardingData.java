package com.nubox.onboarding.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OnboardingData {

    private Integer nextStage;
    private List<Stage> stages = new ArrayList<>();

    public void addOrUpdateStage(Stage stage) {
        stages.stream().filter(item -> item.getId().equals(stage.getId())).findFirst()
                .ifPresentOrElse(item -> item.setElements(stage.getElements()),
                        () -> stages.add(stage));
    }

}
