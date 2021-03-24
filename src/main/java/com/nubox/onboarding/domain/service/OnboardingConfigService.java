package com.nubox.onboarding.domain.service;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingConfigService {

    @Autowired
    private OnboardingConfigRepository onboardingConfigRepository;

    public OnboardingConfig getCurrentConfig() {
        Integer idOfCurrentConfig = onboardingConfigRepository.getIdOfCurrentConfig();
        return onboardingConfigRepository.getConfigById(idOfCurrentConfig);
    }

}
