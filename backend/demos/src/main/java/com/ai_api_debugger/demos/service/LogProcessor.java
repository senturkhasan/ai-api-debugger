package com.ai_api_debugger.demos.service;

import org.springframework.stereotype.Service;

@Service
public class LogProcessor {

    public boolean isValid(String log) {
        if (log == null || log.trim().length() < 15) return false;

        String lower = log.toLowerCase();

        return lower.contains("exception")
                || lower.contains("error")
                || lower.contains("failed")
                || lower.contains("at ")
                || lower.contains("caused by");
    }

    public String clean(String log) {
        return log
                .replaceAll("\\d{4}-\\d{2}-\\d{2}[T ]\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?", "")
                .replaceAll("\\[[^\\]]*\\]", "")
                .replaceAll("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", "UUID") //  
                .replaceAll("\\s+", " ")
                .trim();
    }
}