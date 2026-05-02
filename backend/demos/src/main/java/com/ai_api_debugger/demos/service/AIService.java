package com.ai_api_debugger.demos.service;

import com.ai_api_debugger.demos.dto.DebugResponse;

public interface AIService {
    DebugResponse analyze(String log);
}


