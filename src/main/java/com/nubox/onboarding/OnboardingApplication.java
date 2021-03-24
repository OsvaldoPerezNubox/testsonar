package com.nubox.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nubox")
public class OnboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingApplication.class, args);
    }

}
