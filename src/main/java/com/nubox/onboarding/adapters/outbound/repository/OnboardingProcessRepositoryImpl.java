package com.nubox.onboarding.adapters.outbound.repository;

import com.nubox.onboarding.adapters.outbound.model.OnboardingProcessEntity;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import com.nubox.onboarding.domain.repository.OnboardingProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OnboardingProcessRepositoryImpl implements OnboardingProcessRepository {

    @Autowired
    private OnboardingProcessRepositoryMyBatis onboardingProcessRepository;

    @Override
    public Optional<OnboardingProcess> findByCompany(Long companyId) {
        return onboardingProcessRepository.findByCompany(companyId).map(OnboardingProcessEntity::toDomain);
    }

    @Override
    public OnboardingProcess save(OnboardingProcess onboardingProcess) {
        return onboardingProcessRepository.save(new OnboardingProcessEntity(onboardingProcess)).toDomain();
    }

    @Override
    public void update(OnboardingProcess onboardingProcess) {
        onboardingProcessRepository.update(new OnboardingProcessEntity(onboardingProcess));
    }

}
