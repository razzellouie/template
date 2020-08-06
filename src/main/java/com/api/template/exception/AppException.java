package com.api.template.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class AppException {
    
    @Autowired
    private CustomException customException;

    public void throwGeneralException() throws Exception {
        final Exception e = new Exception("Error from General Exception");
        throw e;
    }

    public Exception userDenied() throws CustomException {
        customException.setCode(10);
        customException.setMessage("User account status is denied");
        throw customException;
    }

    public Exception userPending() throws CustomException {
        customException.setCode(10);
        customException.setMessage("User account status is pending");
        throw customException;
    }

    public Exception userDeactivated() throws CustomException {
        customException.setCode(10);
        customException.setMessage("User account status is deactivated");
        throw customException;
    }
   
}