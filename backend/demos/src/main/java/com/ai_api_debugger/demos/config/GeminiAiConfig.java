package com.ai_api_debugger.demos.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("gemini")

@Getter
@Setter
public class GeminiAiConfig {

    private String apiKey;

}
