package com.example.featuretoggle.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.featuretoggle.FeatureToggles;
import com.example.featuretoggle.annotation.FeatureToggle;
import com.example.featuretoggle.exception.FeatureToggleNotEnabledException;
import com.example.featuretoggle.service.impl.FeatureToggleServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {
  private final FeatureToggleServiceImpl featureToggleService;

    @Around(value = "@annotation(featureToggle)")
    public Object checkFeatureToggleAnnotation(ProceedingJoinPoint joinPoint, FeatureToggle featureToggle) throws Throwable {
        return checkToggle(joinPoint, featureToggle);
    }

    private Object checkToggle(ProceedingJoinPoint joinPoint, FeatureToggle featureToggle) throws Throwable {
        FeatureToggles toggle = featureToggle.value();

        if (featureToggleService.check(toggle.getFeatureName())) {
            return joinPoint.proceed();
        }
        log.warn("Feature toggle {} is not enabled!", toggle.getFeatureName());
        throw new FeatureToggleNotEnabledException(toggle.getFeatureName());
    }

    @Before("@annotation(featureToggle)")
    public void checkFeatureToggle(JoinPoint joinPoint, FeatureToggle featureToggle) {
        FeatureToggles toggle = featureToggle.value();
        if (!featureToggleService.check(toggle.getFeatureName())) {
        throw new FeatureToggleNotEnabledException(toggle.getFeatureName());
        }
    }
}
