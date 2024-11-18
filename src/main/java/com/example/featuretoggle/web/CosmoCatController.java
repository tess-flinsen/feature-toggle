package com.example.featuretoggle.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.featuretoggle.FeatureToggles;
import com.example.featuretoggle.annotation.FeatureToggle;
import com.example.featuretoggle.service.CosmoCatService;

import java.util.List;

@RestController
@RequestMapping("/v1/cosmocats")
public class CosmoCatController {

    private final CosmoCatService cosmoCatService;

    public CosmoCatController(CosmoCatService cosmoCatService) {
        this.cosmoCatService = cosmoCatService;
    }

    @GetMapping
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public ResponseEntity<List<String>> getAllCosmoCats() {
        List<String> cosmoCats = cosmoCatService.getCosmoCats();
        return ResponseEntity.ok(cosmoCats);
    }
}