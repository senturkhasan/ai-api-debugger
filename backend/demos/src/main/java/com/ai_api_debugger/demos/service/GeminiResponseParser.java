package com.ai_api_debugger.demos.service;

import com.ai_api_debugger.demos.dto.DebugResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GeminiResponseParser {

    private static final Logger logger = LoggerFactory.getLogger(GeminiResponseParser.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public DebugResponse parse(String raw) {
        try {
            logger.info("Parsing Gemini response: {}", raw);
            
            // First, try to parse as direct JSON (new format)
            if (raw.trim().startsWith("{")) {
                try {
                    return mapper.readValue(raw, DebugResponse.class);
                } catch (Exception e) {
                    logger.debug("Failed to parse as direct JSON, trying nested format", e);
                }
            }
            
            // Then, try the old nested format (candidates > content > parts > text)
            JsonNode root = mapper.readTree(raw);
            
            String text = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            logger.info("Extracted text from nested format: {}", text);
            
            // JSON extract from nested text
            int start = text.indexOf("{");
            int end = text.lastIndexOf("}") + 1;

            if (start >= 0 && end > start) {
                String json = text.substring(start, end);
                return mapper.readValue(json, DebugResponse.class);
            }

        } catch (Exception e) {
            logger.error("Error parsing Gemini response: {}", e.getMessage(), e);
        }
        
        // Fallback response
        DebugResponse fallback = new DebugResponse();
        fallback.setRootCause("Parse error");
        fallback.setFix("Try again with clearer log");
        fallback.setSeverity("UNKNOWN");
        return fallback;
    }
}

