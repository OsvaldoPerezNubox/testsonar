package com.nubox.onboarding.domain.model.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter @Setter
public class OnboardingConfig {

    private Integer id;
    private String name;
    private Integer regionId;
    private List<StageConfig> stages;

    public Optional<StageConfig> getStageById(Integer id) {
        return stages.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public boolean isFirstStage(Integer stageConfigId) {
        return stages.get(0).getId().equals(stageConfigId);
    }

    public boolean isLastStage(Integer stageConfigId) {
        return stages.get(stages.size() - 1).getId().equals(stageConfigId);
    }

    public Integer nextStage(StageConfig stageConfig) {
        return stages.get(stages.indexOf(stageConfig) + 1).getId();
    }

}
