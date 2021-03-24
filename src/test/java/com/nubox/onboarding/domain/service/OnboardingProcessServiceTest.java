package com.nubox.onboarding.domain.service;

import com.nubox.onboarding.domain.model.ElementData;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import com.nubox.onboarding.domain.model.Stage;
import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import com.nubox.onboarding.domain.repository.OnboardingProcessRepository;
import com.nubox.onboarding.util.Mocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OnboardingProcessServiceTest {

    @Mock
    private OnboardingConfigRepository onboardingConfigRepository;

    @Mock
    private OnboardingProcessRepository onboardingProcessRepository;

    @InjectMocks
    private OnboardingProcessService onboardingProcessService;

    @Test
    void findByCompany_Expect_OptionalEmpty() {

        //Given
        given(onboardingProcessRepository.findByCompany(anyLong())).willReturn(Optional.empty());

        //When
        Optional<OnboardingProcess> result = onboardingProcessService.findByCompany(1L);

        //Then
        then(result).isEmpty();
    }

    @Test
    void findWithConfigByCompany_OK() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        given(onboardingConfigRepository.getConfigById(anyInt())).willReturn(Mocks.getOnboardingConfig());

        //When
        Optional<OnboardingProcess> result = onboardingProcessService.findWithConfigByCompany(COMPANY_ID);

        //Then
        then(result).isPresent();
        then(result.get().getCompanyId()).isEqualTo(COMPANY_ID);
        then(result.get().getConfig()).isNotNull();
    }

    @Test
    void save_OK() {

        //Given
        final Long COMPANY_ID = 1L;
        final Integer ID_OF_CURRENT_CONFIG = 1;
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.empty());
        given(onboardingConfigRepository.getIdOfCurrentConfig()).willReturn(ID_OF_CURRENT_CONFIG);
        given(onboardingConfigRepository.getConfigById(ID_OF_CURRENT_CONFIG)).willReturn(Mocks.getOnboardingConfig());
        ArgumentCaptor<OnboardingProcess> opArgumentCaptor = ArgumentCaptor.forClass(OnboardingProcess.class);

        Stage stageRequest = Mocks.getStageRequest().toDomain();

        //When
        onboardingProcessService.save(COMPANY_ID, stageRequest);

        //Then
        verify(onboardingProcessRepository).save(opArgumentCaptor.capture());
        OnboardingProcess result = opArgumentCaptor.getValue();
        then(result).isNotNull();
        then(result.isCompleted()).isFalse();
        then(result.getCompanyId()).isEqualTo(COMPANY_ID);
        then(result.getConfigId()).isEqualTo(ID_OF_CURRENT_CONFIG);
        then(result.getData().getNextStage()).isEqualTo(2);
        then(result.getData().getStages().size()).isEqualTo(1);
    }

    @Test
    void save_With_ExistingOnboarding() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        Stage stageRequest = Mocks.getStageRequest().toDomain();

        //When
        assertThrows(IllegalStateException.class, () -> onboardingProcessService.save(COMPANY_ID, stageRequest));
    }

    @Test
    void save_With_InvalidStageRequest() {

        //Given
        final Long COMPANY_ID = 1L;
        final Integer ID_OF_CURRENT_CONFIG = 1;
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.empty());
        given(onboardingConfigRepository.getIdOfCurrentConfig()).willReturn(ID_OF_CURRENT_CONFIG);
        given(onboardingConfigRepository.getConfigById(ID_OF_CURRENT_CONFIG)).willReturn(Mocks.getOnboardingConfig());
        Stage stageRequest = Mocks.getStageRequest().toDomain();
        stageRequest.setId(2);

        //When
        assertThrows(IllegalArgumentException.class, () -> onboardingProcessService.save(COMPANY_ID, stageRequest));
    }

    @Test
    void update_OK() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        given(onboardingConfigRepository.getConfigById(anyInt())).willReturn(Mocks.getOnboardingConfig());
        ArgumentCaptor<OnboardingProcess> opArgumentCaptor = ArgumentCaptor.forClass(OnboardingProcess.class);
        Stage stageRequest = Mocks.getStageRequest().toDomain();
        stageRequest.setId(2);

        //When
        onboardingProcessService.update(COMPANY_ID, stageRequest);

        //Then
        verify(onboardingProcessRepository).update(opArgumentCaptor.capture());
        OnboardingProcess result = opArgumentCaptor.getValue();
        then(result).isNotNull();
        then(result.isCompleted()).isTrue();
        then(result.getCompanyId()).isEqualTo(COMPANY_ID);
        then(result.getData().getNextStage()).isEqualTo(2);
        then(result.getData().getStages().size()).isEqualTo(2);
    }

    @Test
    void update_With_OnboardingCompleted() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        mock.setCompleted(true);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        given(onboardingConfigRepository.getConfigById(anyInt())).willReturn(Mocks.getOnboardingConfig());
        Stage stageRequest = Mocks.getStageRequest().toDomain();

        //When
        assertThrows(IllegalArgumentException.class, () -> onboardingProcessService.update(COMPANY_ID, stageRequest));
    }

    @Test
    void update_With_InvalidStageRequest() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        mock.getData().setNextStage(1);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        given(onboardingConfigRepository.getConfigById(anyInt())).willReturn(Mocks.getOnboardingConfig());
        Stage stageRequest = Mocks.getStageRequestWithInvalidElement().toDomain();
        stageRequest.setId(2);

        //When
        assertThrows(IllegalArgumentException.class, () -> onboardingProcessService.update(COMPANY_ID, stageRequest));
    }

    @Test
    void update_With_InvalidElementData() {

        //Given
        final Long COMPANY_ID = 1L;
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        mock.setCompleted(true);
        given(onboardingProcessRepository.findByCompany(COMPANY_ID)).willReturn(Optional.of(mock));
        given(onboardingConfigRepository.getConfigById(anyInt())).willReturn(Mocks.getOnboardingConfig());
        Stage stageRequest = Mocks.getStageRequestWithInvalidElement().toDomain();

        //When
        assertThrows(IllegalArgumentException.class, () -> onboardingProcessService.update(COMPANY_ID, stageRequest));
    }

}