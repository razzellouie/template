package com.api.template.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginRequest {

    @NotNull(message = "Please provide email")
    private String email;

    @NotNull(message = "Please provide password")
    private String password;
}