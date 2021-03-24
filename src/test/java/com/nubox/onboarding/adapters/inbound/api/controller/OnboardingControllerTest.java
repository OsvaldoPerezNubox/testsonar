package com.nubox.onboarding.adapters.inbound.api.controller;

import com.nubox.core.common.exception.RestExceptionHandler;
import com.nubox.onboarding.adapters.inbound.api.request.ElementRequest;
import com.nubox.onboarding.adapters.inbound.api.request.StageRequest;
import com.nubox.onboarding.domain.model.OnboardingProcess;
import com.nubox.onboarding.domain.model.Stage;
import com.nubox.onboarding.domain.service.OnboardingConfigService;
import com.nubox.onboarding.domain.service.OnboardingProcessService;
import com.nubox.onboarding.util.Mocks;
import com.nubox.onboarding.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class OnboardingControllerTest {

    private static final String PATH = "/employee";

    private MockMvc mockMvc;

    @Mock
    private OnboardingConfigService onboardingConfigService;

    @Mock
    private OnboardingProcessService onboardingProcessService;

    @InjectMocks
    private OnboardingController onboardingController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(onboardingController)
                .setControllerAdvice(RestExceptionHandler.class)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getCurrentConfig() throws Exception {

        //Given
        final String PATH = "/onboarding/configuration";
        given(onboardingConfigService.getCurrentConfig()).willReturn(Mocks.getOnboardingConfig());

        //When
        mockMvc.perform(get(PATH))

        //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.stages").exists());
    }

    @Test
    void getOnboardingProcessByCompanyId_Expect_200() throws Exception {

        //Given
        final Long COMPANY_ID = 23L;
        final String PATH = "/company/" + COMPANY_ID + "/onboarding";
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        given(onboardingProcessService.findByCompany(anyLong())).willReturn(Optional.of(mock));

        //When
        mockMvc.perform(get(PATH))

        //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.companyId").value(COMPANY_ID))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.config").doesNotExist());
    }

    @Test
    void getOnboardingProcessByCompanyId_Expect_204() throws Exception {

        //Given
        final String PATH = "/company/23/onboarding";
        given(onboardingProcessService.findByCompany(anyLong())).willReturn(Optional.empty());

        //When
        mockMvc.perform(get(PATH))

        //Then
                .andExpect(status().isNoContent());
    }

    @Test
    void getOnboardingProcessWithConfigBy_Expect_200() throws Exception {

        //Given
        final Long COMPANY_ID = 23L;
        final String PATH = "/company/" + COMPANY_ID + "/onboarding-with-config";
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        mock.setConfig(Mocks.getOnboardingConfig());
        given(onboardingProcessService.findWithConfigByCompany(anyLong())).willReturn(Optional.of(mock));

        //When
        mockMvc.perform(get(PATH))

        //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.companyId").value(COMPANY_ID))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.config").isNotEmpty());
    }

    @Test
    void saveOnboardingProcess() throws Exception {

        //Given
        final Long COMPANY_ID = 23L;
        final String PATH = "/company/" + COMPANY_ID + "/onboarding";
        OnboardingProcess mock = Mocks.getOnboardingProcess(COMPANY_ID);
        given(onboardingProcessService.save(anyLong(), any(Stage.class))).willReturn(mock);

        //When
        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.asJsonString(Mocks.getStageRequest())))

        //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.companyId").value(COMPANY_ID));
    }

    @Test
    void updateOnboardingProcess() throws Exception {

        //When
        mockMvc.perform(patch("/company/23/onboarding")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.asJsonString(Mocks.getStageRequest())))

        //Then
                .andExpect(status().isOk());

        verify(onboardingProcessService).update(anyLong(), any(Stage.class));
    }

}