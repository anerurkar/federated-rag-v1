package com.example.federatedrag.controller;

import com.example.federatedrag.service.FederatedQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    private final FederatedQueryService federatedQueryService;

    public AskController(FederatedQueryService federatedQueryService) {
        this.federatedQueryService = federatedQueryService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return federatedQueryService.ask(q);
    }
}