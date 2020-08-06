package com.api.template.response;

import lombok.Data; 

@Data 
public class CustomResponse {

    private boolean success;
    private String msg;

    public CustomResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

}