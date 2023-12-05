package com.example.php_asm1.models.ForgotPassword;

public class ForgotPasswordRequest {
    private String email;

    public ForgotPasswordRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }
}
