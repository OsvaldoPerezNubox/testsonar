package com.nubox.onboarding.util;

import com.nubox.onboarding.adapters.inbound.api.request.ElementRequest;
import com.nubox.onboarding.adapters.inbound.api.request.StageRequest;
import com.nubox.onboarding.adapters.outbound.model.OnboardingProcessEntity;
import com.nubox.onboarding.domain.model.ElementData;
import com.nubox.onboarding.domain.model.OnboardingData;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import com.nubox.onboarding.domain.model.Stage;
import com.nubox.onboarding.domain.model.config.FormElement;
import com.nubox.onboarding.domain.model.config.FormOption;
import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.domain.model.config.StageConfig;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mocks {

    public static OnboardingConfig getOnboardingConfig() {
        OnboardingConfig onboardingConfig = new OnboardingConfig();
        onboardingConfig.setId(1);
        onboardingConfig.setName("onboarding-name");
        onboardingConfig.setRegionId(1);
        onboardingConfig.setStages(List.of(getStageConfig(1), getStageConfig(2)));
        return onboardingConfig;
    }

    private static StageConfig getStageConfig(Integer stageId) {
        StageConfig stageConfig = new StageConfig();
        stageConfig.setId(stageId);
        stageConfig.setTitle("title");
        stageConfig.setOrder(stageId);
        FormElement formElement = new FormElement();
        formElement.setId(1);
        formElement.setLabel("label");
        formElement.setType("INPUT");
        formElement.setRequired(true);
        formElement.setOrder(1);
        FormOption formOption = new FormOption();
        formOption.setId(1);
        formOption.setValue("value");
        formElement.setOptions(List.of(formOption));
        stageConfig.setFormElements(List.of(formElement));
        return stageConfig;
    }

    public static OnboardingProcess getOnboardingProcess(Long companyId) {
        OnboardingProcess onboardingProcess = new OnboardingProcess();
        onboardingProcess.setId(1L);
        onboardingProcess.setCompanyId(companyId);
        onboardingProcess.setConfigId(1);
        OnboardingData data = new OnboardingData();
        data.setNextStage(2);
        Stage stage = new Stage();
        stage.setId(1);
        ElementData elementData = new ElementData();
        elementData.setId(1);
        elementData.setLabel("Antiguedad de la empresa");
        elementData.setValues(List.of("2 años"));
        stage.setElements(List.of(elementData));
        data.getStages().add(stage);
        onboardingProcess.setData(data);
        return onboardingProcess;
    }

    public static OnboardingProcessEntity getOnboardingProcessEntity() {
        OnboardingProcessEntity onboardingProcessEntity = new OnboardingProcessEntity();
        onboardingProcessEntity.setId(1L);
        onboardingProcessEntity.setUserId("user-id");
        onboardingProcessEntity.setCompanyId(1L);
        onboardingProcessEntity.setConfigId(1);
        onboardingProcessEntity.setCompleted(true);
        onboardingProcessEntity.setCreatedDate(OffsetDateTime.now());
        onboardingProcessEntity.setData("{\n" +
                "      \"nextStage\":2,\n" +
                "      \"stages\":[\n" +
                "         {\n" +
                "            \"id\":1,\n" +
                "            \"elements\":[\n" +
                "               {\n" +
                "                  \"id\":1,\n" +
                "                  \"label\":\"Antiguedad de la empresa\",\n" +
                "                  \"values\":[\n" +
                "                     \"2 años\"\n" +
                "                  ]\n" +
                "               },\n" +
                "               {\n" +
                "                  \"id\":2,\n" +
                "                  \"label\":\"Actividad de la empresa\",\n" +
                "                  \"values\":[\n" +
                "                     \"Venta de insumos\"\n" +
                "                  ]\n" +
                "               }\n" +
                "            ]\n" +
                "         }\n" +
                "      ]\n" +
                "   }");
        return onboardingProcessEntity;
    }

    public static StageRequest getStageRequest() {
        StageRequest request = new StageRequest();
        request.setId(1);
        ElementRequest elementRequest = new ElementRequest();
        elementRequest.setId(1);
        elementRequest.setValues(List.of("value1"));
        request.setElements(List.of(elementRequest));
        return request;
    }

    public static StageRequest getStageRequestWithInvalidElement() {
        StageRequest request = new StageRequest();
        request.setId(1);
        ElementRequest elementRequest = new ElementRequest();
        elementRequest.setId(23);
        elementRequest.setValues(List.of("value1"));
        request.setElements(List.of(elementRequest));
        return request;
    }

}
