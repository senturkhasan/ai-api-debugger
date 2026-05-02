package com.ai_api_debugger.demos.controller;

import com.ai_api_debugger.demos.dto.ApiResponse;
import com.ai_api_debugger.demos.dto.DebugResponse;
import com.ai_api_debugger.demos.dto.LogRequest;
import com.ai_api_debugger.demos.service.AIService;
import com.ai_api_debugger.demos.service.LogProcessor;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    private final LogProcessor logProcessor;
    private final AIService aiService;

    public DebugController(LogProcessor logProcessor,
                           AIService aiService) {
        this.logProcessor = logProcessor;
        this.aiService = aiService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DebugResponse>> debug(@Valid @RequestBody LogRequest request) {
        log.info("Received log for debugging");
        String logMessage = request.getLog();

        if (!logProcessor.isValid(logMessage)) {
            DebugResponse res = new DebugResponse();

            res.setRootCause("Unrecognized log format.");
            res.setFix("The input does not match a standard log pattern. " +
                    "Please provide a technical log trace. Example: " +
                    "'2026-05-02 14:00:00 ERROR [main] com.myapp.Service - NullPointerException at line 42'");
            res.setSeverity("LOW");

            return ResponseEntity.ok(ApiResponse.success(res));
        }

        String cleaned = logProcessor.clean(logMessage);
        log.info("Cleaned log: {}", cleaned);
        
        DebugResponse response = aiService.analyze(cleaned);
        log.info("AI response: rootCause={}, fix={}, severity={}", 
            response.getRootCause(), 
            response.getFix(), 
            response.getSeverity());
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}


