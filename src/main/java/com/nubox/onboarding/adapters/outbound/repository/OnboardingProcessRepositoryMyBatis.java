package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.adapters.outbound.model.OnboardingProcessEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OnboardingProcessRepositoryMyBatis {

    Optional<OnboardingProcessEntity> findByCompany(Long companyId);

    OnboardingProcessEntity save(OnboardingProcessEntity onboardingProcessEntity);

    void update(OnboardingProcessEntity onboardingProcessEntity);
}
