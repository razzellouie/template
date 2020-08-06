package com.api.template.request; 
import com.api.template.model.User;
import com.api.template.model.Admin;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    
    private String accessToken;
    private String tokenType = "Bearer";
    private String userType;

    public JwtAuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        if(user instanceof Admin) this.userType = "Admin";
    }

}
