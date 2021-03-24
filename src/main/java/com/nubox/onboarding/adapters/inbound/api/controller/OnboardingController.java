package com.nubox.onboarding.adapters.inbound.api.controller;

import com.nubox.onboarding.adapters.inbound.api.request.StageRequest;
import com.nubox.onboarding.adapters.inbound.api.response.OnboardingProcessResponse;
import com.nubox.onboarding.domain.model.config.OnboardingConfig;
import com.nubox.onboarding.domain.service.OnboardingConfigService;
import com.nubox.onboarding.domain.service.OnboardingProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OnboardingController {

    @Autowired
    private OnboardingConfigService onboardingConfigService;

    @Autowired
    private OnboardingProcessService onboardingProcessService;

    @GetMapping("onboarding/configuration")
    public OnboardingConfig getCurrentConfig() {
        return onboardingConfigService.getCurrentConfig();
    }

    @GetMapping("company/{companyId}/onboarding")
    public ResponseEntity<OnboardingProcessResponse> getOnboardingProcessBy(@PathVariable Long companyId) {
        return onboardingProcessService.findByCompany(companyId)
                .map(op -> ResponseEntity.ok(OnboardingProcessResponse.build(op)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("company/{companyId}/onboarding-with-config")
    public ResponseEntity<OnboardingProcessResponse> getOnboardingProcessWithConfigBy(@PathVariable Long companyId) {
        return onboardingProcessService.findWithConfigByCompany(companyId)
                .map(op -> ResponseEntity.ok(OnboardingProcessResponse.build(op)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("company/{companyId}/onboarding")
    public OnboardingProcessResponse saveOnboardingProcess(@PathVariable Long companyId, @Valid @RequestBody StageRequest request) {
        return OnboardingProcessResponse.build(onboardingProcessService.save(companyId, request.toDomain()));
    }

    @PatchMapping("company/{companyId}/onboarding")
    public void updateOnboardingProcess(@PathVariable Long companyId, @Valid @RequestBody StageRequest request) {
        onboardingProcessService.update(companyId, request.toDomain());
    }

}
