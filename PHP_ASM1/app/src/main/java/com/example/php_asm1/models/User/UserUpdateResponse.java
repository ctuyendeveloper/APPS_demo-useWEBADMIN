package com.example.php_asm1.models.User;

public class UserUpdateResponse {
    Boolean status;
    String message;

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

    public UserUpdateResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
