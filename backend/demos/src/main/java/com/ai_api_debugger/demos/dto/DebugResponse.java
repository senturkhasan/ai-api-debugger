package com.ai_api_debugger.demos.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebugResponse {

    private String rootCause;
    private String fix;
    private String severity;
}
