package com.ai_api_debugger.demos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {

    @NotBlank(message = "Log cannot be empty")
    @Size(min = 10, max = 50000, message = "Log must be between 10 and 50000 characters")
    private String log;
}

