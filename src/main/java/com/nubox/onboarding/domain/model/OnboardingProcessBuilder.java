package com.nubox.onboarding.domain.model;

import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OnboardingProcessBuilder {

    public static OnboardingProcess build(Long companyId,Integer stageId, OnboardingConfigRepository onboardingConfigRepository) {
        OnboardingProcess onboardingProcess = new OnboardingProcess();
        onboardingProcess.setCompanyId(companyId);
        onboardingProcess.setData(new OnboardingData());
        onboardingProcess.getData().setNextStage(stageId);
        onboardingProcess.setUserId("0da56ec1-19c9-436b-b29d-5ce9f6861dec");
        Integer configId = onboardingConfigRepository.getIdOfCurrentConfig();
        onboardingProcess.setConfigId(configId);
        onboardingProcess.setConfig(onboardingConfigRepository.getConfigById(configId));
        return onboardingProcess;
    }

}
