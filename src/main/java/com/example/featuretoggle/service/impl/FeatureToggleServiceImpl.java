package com.example.featuretoggle.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import com.example.featuretoggle.config.FeatureToggleProperties;

@Service
public class FeatureToggleServiceImpl {

    private final ConcurrentHashMap<String, Boolean> featureToggles;

    public FeatureToggleServiceImpl(FeatureToggleProperties featureToggleProperties) {
        featureToggles = new ConcurrentHashMap<>(featureToggleProperties.getToggles());
    }

    public boolean check(String featureName) {
        return featureToggles.getOrDefault(featureName, false);
    }

    public void enable(String featureName) {
        featureToggles.put(featureName, true);
    }

    public void disable(String featureName) {
        featureToggles.put(featureName, false);
    }
}
