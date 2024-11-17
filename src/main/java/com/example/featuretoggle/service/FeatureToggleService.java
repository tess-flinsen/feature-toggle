package com.example.featuretoggle.service;

public interface FeatureToggleService {
    boolean check(String featureName);
    void enable(String featureName);
    void disable(String featureName);
}
