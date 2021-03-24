package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.util.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OnboardingConfigRepositoryImplTest {

    @Mock
    private OnboardingConfigRepositoryMyBatis ocRepositoryMyBatis;

    @InjectMocks
    private OnboardingConfigRepositoryImpl onboardingConfigRepository;

    private final Integer ID_OF_CURRENT_CONFIG = 1;

    @Test
    void getIdOfCurrentConfig() {

        //Given
        given(ocRepositoryMyBatis.getIdOfCurrentConfig(anyInt())).willReturn(Optional.of(ID_OF_CURRENT_CONFIG));

        //When
        Integer result = onboardingConfigRepository.getIdOfCurrentConfig();

        //Then
        then(result).isEqualTo(ID_OF_CURRENT_CONFIG);
    }

    @Test
    void getConfigById() {

        //Given
        OnboardingConfig mock = Mocks.getOnboardingConfig();
        given(ocRepositoryMyBatis.getConfigById(anyInt())).willReturn(Optional.of(mock));

        //When
        OnboardingConfig result = onboardingConfigRepository.getConfigById(ID_OF_CURRENT_CONFIG);

        //Then
        then(result.getId()).isEqualTo(ID_OF_CURRENT_CONFIG);
    }

}