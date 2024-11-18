package com.example.featuretoggle.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.example.featuretoggle.FeatureToggleExtension;
import com.example.featuretoggle.FeatureToggles;
import com.example.featuretoggle.annotation.DisabledFeatureToggle;
import com.example.featuretoggle.annotation.EnabledFeatureToggle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Cosmo Cats Controller IT")
@ExtendWith(FeatureToggleExtension.class)
class CosmoCatControllerIT{
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldGet404FeatureDisabled() throws Exception {
        mockMvc.perform(get("/v1/cosmo-cats")).andExpect(status().isNotFound());
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldGet200() throws Exception {
        mockMvc.perform(get("/v1/cosmo-cats")).andExpect(status().isOk());
    }
}
