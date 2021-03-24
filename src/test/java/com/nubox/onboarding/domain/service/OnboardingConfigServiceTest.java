package com.nubox.onboarding.domain.service;

import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import com.nubox.onboarding.util.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OnboardingConfigServiceTest {

    @Mock
    private OnboardingConfigRepository onboardingConfigRepository;

    @InjectMocks
    private OnboardingConfigService onboardingConfigService;

    @Test
    void getCurrentConfig_OK() {

        //Given
        final Integer ID_OF_CURRENT_CONFIG = 1;
        OnboardingConfig mock = Mocks.getOnboardingConfig();
        given(onboardingConfigRepository.getIdOfCurrentConfig()).willReturn(ID_OF_CURRENT_CONFIG);
        given(onboardingConfigRepository.getConfigById(ID_OF_CURRENT_CONFIG)).willReturn(mock);

        //When
        OnboardingConfig result = onboardingConfigService.getCurrentConfig();

        //Then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(ID_OF_CURRENT_CONFIG);
    }

}