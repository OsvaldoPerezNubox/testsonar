package com.nubox.onboarding.domain.repository;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;

public interface OnboardingConfigRepository {

    Integer getIdOfCurrentConfig();

    OnboardingConfig getConfigById(Integer id);
}
