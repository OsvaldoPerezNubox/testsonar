package com.nubox.onboarding.adapters.inbound.api.response;

import com.nubox.onboarding.domain.model.OnboardingData;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
public class OnboardingProcessResponse {

    private Long id;
    private String userId;
    private Long companyId;
    private OnboardingData data;
    private boolean completed;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private Integer configId;
    private OnboardingConfig config;

    public static OnboardingProcessResponse build(OnboardingProcess onboardingProcess) {
        OnboardingProcessResponse response = new OnboardingProcessResponse();
        response.setId(onboardingProcess.getId());
        response.setUserId(onboardingProcess.getUserId());
        response.setCompanyId(onboardingProcess.getCompanyId());
        response.setData(onboardingProcess.getData());
        response.setCompleted(onboardingProcess.isCompleted());
        response.setCreatedDate(onboardingProcess.getCreatedDate());
        response.setModifiedDate(onboardingProcess.getModifiedDate());
        response.setConfigId(onboardingProcess.getConfigId());
        if (onboardingProcess.getConfig() != null) response.setConfig(onboardingProcess.getConfig());
        return response;
    }

}
