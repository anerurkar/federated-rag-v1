package com.example.federatedrag.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FederatedQueryService {

    private final VectorStore vectorStore;
    private final RoutingService routingService;

    public FederatedQueryService(VectorStore vectorStore, RoutingService routingService) {
        this.vectorStore = vectorStore;
        this.routingService = routingService;
    }

    public String ask(String query) {
        List<String> domains = routingService.detectDomains(query);
        List<Document> merged = new ArrayList<>();

        for (String domain : domains) {
            SearchRequest request = SearchRequest.builder()
                    .query(query)
                    .topK(2)
                    .filterExpression("domain == '" + domain + "'")
                    .build();

            List<Document> docs = vectorStore.similaritySearch(request);

            if (docs != null) {
                merged.addAll(docs);
            }
        }

        return merged.stream()
                .limit(4)
                .map(d -> "[" + d.getMetadata().get("domain") + "] " + d.getText())
                .collect(Collectors.joining("\n"));
    }
}