package com.example.federatedrag.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoutingService {

    public List<String> detectDomains(String query) {
        String q = query.toLowerCase();
        List<String> domains = new ArrayList<>();

        if (q.contains("kyc") || q.contains("edd") || q.contains("sanction")) {
            domains.add("kyc");
        }

        if (q.contains("loan") || q.contains("salary") || q.contains("disbursement")) {
            domains.add("lending");
        }

        if (domains.isEmpty()) {
            domains.add("kyc");
            domains.add("lending");
        }

        return domains;
    }
}