package com.nubox.onboarding.domain.repository;

import com.nubox.onboarding.domain.model.OnboardingProcess;

import java.util.Optional;

public interface OnboardingProcessRepository {

    Optional<OnboardingProcess> findByCompany(Long companyId);

    OnboardingProcess save(OnboardingProcess onboardingProcess);

    void update(OnboardingProcess onboardingProcess);
}
