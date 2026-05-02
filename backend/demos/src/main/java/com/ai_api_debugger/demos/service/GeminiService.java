package com.ai_api_debugger.demos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai_api_debugger.demos.dto.DebugResponse;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService implements AIService {
    private final Client client;

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }
    
    @Override
    public DebugResponse analyze(String log) {

        String prompt = """
            You are an expert Backend Debugger. 
            Analyze the provided log and return a JSON response.
        
            SCENARIOS:
            1. If the input is a valid stacktrace or error:
               - Identify the 'rootCause'.
               - Provide a step-by-step 'fix'.
               - Set 'severity' (LOW, MEDIUM, HIGH).
        
            2. If the input is NOT a technical log (e.g., random text, greetings, or insufficient data):
               - Set 'rootCause' to 'The provided input does not contain a recognizable error log or stacktrace.'
               - Set 'fix' to 'Please provide a complete error message or a stacktrace from your console/logs to get an accurate analysis.'
               - Set 'severity' to 'UNKNOWN'
        
            Return ONLY raw JSON:
            {
              "rootCause": "...",
              "fix": "...",
              "severity": "..."
            }
        
            LOG CONTENT:
            """ + log;
        
        logger.info("Sending request to Gemini API");
        GenerateContentResponse response = client.models.generateContent("gemini-3-flash-preview",
                prompt,
                null);
        
        String rawResponse = response.text();
        logger.info("Received raw response from Gemini: {}", rawResponse);
        
        // Parser ile DebugResponse'a dönüştür
        DebugResponse debugResponse = new GeminiResponseParser().parse(rawResponse);
        logger.info("Parsed response: rootCause={}, fix={}, severity={}", 
            debugResponse.getRootCause(), 
            debugResponse.getFix(), 
            debugResponse.getSeverity());
        
        return debugResponse;
    }
}


