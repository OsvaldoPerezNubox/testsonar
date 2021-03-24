package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface OnboardingConfigRepositoryMyBatis {

    Optional<Integer> getIdOfCurrentConfig(Integer regionId);

    Optional<OnboardingConfig> getConfigById(Integer id);
}
