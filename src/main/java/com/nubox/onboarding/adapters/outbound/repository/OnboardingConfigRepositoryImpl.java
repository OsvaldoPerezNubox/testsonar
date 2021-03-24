package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OnboardingConfigRepositoryImpl implements OnboardingConfigRepository {

    //TODO: revisar por donde vendra este valor
    private static final Integer REGION_ID = 1;

    @Autowired
    private OnboardingConfigRepositoryMyBatis onboardingConfigRepository;


    @Override
    public Integer getIdOfCurrentConfig() {
        return onboardingConfigRepository.getIdOfCurrentConfig(REGION_ID).orElseThrow();
    }

    @Override
    public OnboardingConfig getConfigById(Integer id) {
        return onboardingConfigRepository.getConfigById(id).orElseThrow();
    }

}
