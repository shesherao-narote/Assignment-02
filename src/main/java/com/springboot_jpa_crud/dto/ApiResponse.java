package com.springboot_jpa_crud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {

    String message;

    Boolean success;

    public ApiResponse(String message, boolean success) {
        this.message=message;
        this.success=success;
    }
}
