package com.example.federatedrag.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class IngestionService implements CommandLineRunner {

    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        ingest("src/main/resources/data/kyc/kyc-policy.txt", "kyc");
        ingest("src/main/resources/data/lending/lending-policy.txt", "lending");
    }

    private void ingest(String path, String domain) {
        try {
            String text = Files.readString(Path.of(path));
            Document document = new Document(text);
            document.getMetadata().put("domain", domain);
            vectorStore.add(List.of(document));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}