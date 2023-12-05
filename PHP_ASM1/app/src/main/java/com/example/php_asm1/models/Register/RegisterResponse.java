package com.example.php_asm1.models.Register;

public class RegisterResponse {
    private Boolean status;
    private String message;

    public RegisterResponse() {
    }

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
}
