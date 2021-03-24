package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.adapters.outbound.model.OnboardingProcessEntity;
import com.nubox.onboarding.domain.model.OnboardingProcess;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OnboardingProcessRepositoryImplTest {

    @Mock
    private OnboardingProcessRepositoryMyBatis opRepositoryMyBatis;

    @InjectMocks
    private OnboardingProcessRepositoryImpl onboardingProcessRepository;

    @Test
    void findByCompany() {

        //Given
        final Long COMPANY_ID = 23L;
        OnboardingProcessEntity mock = Mocks.getOnboardingProcessEntity();
        mock.setCompanyId(COMPANY_ID);
        given(opRepositoryMyBatis.findByCompany(anyLong())).willReturn(Optional.of(mock));

        //When
        Optional<OnboardingProcess> result = onboardingProcessRepository.findByCompany(COMPANY_ID);

        //Then
        then(result).isPresent();
        then(result.get().getCompanyId()).isEqualTo(COMPANY_ID);
        then(result.get()).hasNoNullFieldsOrPropertiesExcept("modifiedDate", "config");
    }

    @Test
    void save() {

        //Given
        final Long COMPANY_ID = 23L;
        OnboardingProcess onboardingProcessMock = Mocks.getOnboardingProcess(COMPANY_ID);
        OnboardingProcessEntity onboardingProcessEntityMock = new OnboardingProcessEntity(onboardingProcessMock);
        given(opRepositoryMyBatis.save(any(OnboardingProcessEntity.class))).willReturn(onboardingProcessEntityMock);

        //When
        OnboardingProcess result = onboardingProcessRepository.save(onboardingProcessMock);

        //Then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(onboardingProcessMock.getId());
        then(result.getCompanyId()).isEqualTo(COMPANY_ID);
    }

    @Test
    void update() {

        //When
        onboardingProcessRepository.update(Mocks.getOnboardingProcess(23L));

        //Then
        verify(opRepositoryMyBatis).update(any(OnboardingProcessEntity.class));
    }

}