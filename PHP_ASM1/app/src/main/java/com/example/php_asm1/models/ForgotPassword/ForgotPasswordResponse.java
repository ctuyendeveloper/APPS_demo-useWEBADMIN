package com.example.php_asm1.models.ForgotPassword;

import com.example.php_asm1.models.User.UserModel;

public class ForgotPasswordResponse {
    private Boolean status;
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForgotPasswordResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ForgotPasswordResponse() {
    }
}
