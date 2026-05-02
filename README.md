# Federated RAG Version1

README - Federated RAG Version1 
Overview

Version 1 is a fully local federated RAG proof of concept built with Spring Boot, Spring AI, PostgreSQL + pgvector, and Ollama embeddings.

It demonstrates:

domain-based intent routing
KYC knowledge hub
Lending knowledge hub
single-domain retrieval
multi-domain federated retrieval
merged response from multiple domain knowledge hubs
Architecture
User Query
   ->
Intent Router
   ->
KYC Knowledge Hub / Lending Knowledge Hub
   ->
Top-K Retrieval
   ->
Merge Results
   ->
Return Response
Prerequisites

Install locally:

Java 17
Maven 3.9+
Docker
Ollama
Start services
Start PostgreSQL + pgvector
docker compose up -d
Pull embedding model
ollama pull nomic-embed-text

Ollama usually runs as a background service. If not running, start it manually.

Run application
mvn spring-boot:run

When startup succeeds, you should see Spring Boot start on port 8080.

Test queries
KYC
curl "http://localhost:8080/ask?q=why%20did%20customer%20trigger%20edd"

Expected:

[kyc] Enhanced due diligence is triggered when PEP match, sanctions hit, or high-risk geography is detected.
Lending
curl "http://localhost:8080/ask?q=what%20is%20minimum%20salary%20for%20personal%20loan"

Expected:

[lending] Personal loan requires minimum monthly salary of INR 25000. Disbursement can be blocked if underwriting exception exists.
Multi-domain
curl "http://localhost:8080/ask?q=loan%20disbursement%20blocked%20after%20kyc%20cleared"

Expected:

[kyc] ...
[lending] ...
What Version 1 proves

Version 1 validates the enterprise retrieval flow:

detect intent from query
route to one or multiple domain knowledge hubs
retrieve relevant chunks
merge domain results
return federated response
Current limitations

Version 1 intentionally keeps the implementation simple.

Not yet included:

chunking
reranking
PDF ingestion
grounded LLM answer generation
operational transaction context

These are introduced in Version 2.
