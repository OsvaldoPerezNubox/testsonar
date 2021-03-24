package com.nubox.onboarding.domain.model;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
public class OnboardingProcess {

    private Long id;
    private String userId;
    private Long companyId;
    private OnboardingData data;
    private boolean completed;
    private OffsetDateTime createdDate = OffsetDateTime.now();
    private OffsetDateTime modifiedDate;
    private Integer configId;
    private OnboardingConfig config;
}
