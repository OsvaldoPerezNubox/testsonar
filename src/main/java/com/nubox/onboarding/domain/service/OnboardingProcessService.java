package com.nubox.onboarding.domain.service;

import com.nubox.onboarding.domain.model.*;
import com.nubox.onboarding.domain.model.config.StageConfig;
import com.nubox.onboarding.domain.repository.OnboardingConfigRepository;
import com.nubox.onboarding.domain.repository.OnboardingProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnboardingProcessService {

    @Autowired
    private OnboardingConfigRepository onboardingConfigRepository;

    @Autowired
    private OnboardingProcessRepository onboardingProcessRepository;

    public Optional<OnboardingProcess> findByCompany(Long companyId) {
        return onboardingProcessRepository.findByCompany(companyId);
    }

    public Optional<OnboardingProcess> findWithConfigByCompany(Long companyId) {
        return onboardingProcessRepository.findByCompany(companyId).map(onboardingProcess -> {
            onboardingProcess.setConfig(onboardingConfigRepository.getConfigById(onboardingProcess.getConfigId()));
            return onboardingProcess;
        });
    }

    public OnboardingProcess save(Long companyId, Stage stageRequest) {
        if (onboardingProcessRepository.findByCompany(companyId).isPresent())
            throw new IllegalStateException("El onboarding para la compañia ya existe.");
        OnboardingProcess op = OnboardingProcessBuilder.build(companyId, stageRequest.getId(), onboardingConfigRepository);
        if (!op.getConfig().isFirstStage(op.getData().getNextStage()))
            throw new IllegalArgumentException("El Stage es inválido");
        validateAndLoadStage(op, stageRequest);
        return onboardingProcessRepository.save(op);
    }

    public void update(Long companyId, Stage stageRequest) {
        OnboardingProcess op = findWithConfigByCompany(companyId).orElseThrow();
        if (op.isCompleted()) throw new IllegalArgumentException("El onboarding ya ha sido completado.");
        validateAndLoadStage(op, stageRequest);
        onboardingProcessRepository.update(op);
    }

    private void validateAndLoadStage(OnboardingProcess op, Stage stageRequest) {
        StageConfig stageConfig = op.getConfig().getStageById(stageRequest.getId()).orElseThrow();
        validateStage(op, stageConfig);
        Stage stageToSave = new Stage(stageRequest.getId());
        validateAndLoadElements(stageConfig, stageToSave, stageRequest.getElements());
        op.getData().addOrUpdateStage(stageToSave);
    }

    private void validateStage(OnboardingProcess op, StageConfig stageConfig) {
        StageConfig nextStageConfigPending = op.getConfig().getStageById(op.getData().getNextStage()).orElseThrow();
        boolean isLoadingNextStagePending = stageConfig.equals(nextStageConfigPending);

        if (!isLoadingNextStagePending
                && stageConfig.getOrder() > nextStageConfigPending.getOrder())
            throw new IllegalArgumentException("El Stage es inválido.");
        if (op.getConfig().isLastStage(stageConfig.getId())) op.setCompleted(true);
        else if (isLoadingNextStagePending) op.getData().setNextStage(op.getConfig().nextStage(stageConfig));
    }

    private void validateAndLoadElements(StageConfig stageConfig, Stage stage, List<ElementData> elements) {
        Map<Integer, ElementData> elementsMap = elements.stream().collect(Collectors.toMap(ElementData::getId, e -> e));
        stageConfig.getFormElements().forEach(formElement -> {
            Optional<ElementData> elementDataOptional = Optional.ofNullable(elementsMap.get(formElement.getId()));
            if (formElement.isRequired() && elementDataOptional.isEmpty()) throw new IllegalArgumentException("El Stage es inválido.");
            elementDataOptional.ifPresent(elementData -> {
                elementData.setLabel(formElement.getLabel());
                stage.addElement(elementData);
            });
        });
    }

}
