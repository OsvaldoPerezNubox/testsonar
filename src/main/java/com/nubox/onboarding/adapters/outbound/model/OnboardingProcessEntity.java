package com.nubox.onboarding.adapters.outbound.model;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.nubox.onboarding.domain.model.OnboardingData;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor
public class OnboardingProcessEntity {

    private Long id;
    private String userId;
    private Long companyId;
    private String data;
    private boolean completed;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private Integer configId;

    private static final Gson gson = new GsonBuilder().create();

    public OnboardingProcessEntity(OnboardingProcess onboardingProcess) {
        id = onboardingProcess.getId();
        userId = onboardingProcess.getUserId();
        companyId = onboardingProcess.getCompanyId();
        configId = onboardingProcess.getConfigId();
        completed = onboardingProcess.isCompleted();
        modifiedDate = onboardingProcess.getModifiedDate();
        data = gson.toJson(onboardingProcess.getData());
    }

    public OnboardingProcess toDomain() {
        OnboardingProcess domain = new OnboardingProcess();
        domain.setId(id);
        domain.setUserId(userId);
        domain.setCompanyId(companyId);
        domain.setCompleted(completed);
        domain.setCreatedDate(createdDate);
        domain.setModifiedDate(modifiedDate);
        domain.setConfigId(configId);
        domain.setData(gson.fromJson(data, OnboardingData.class));
        return domain;
    }

}
