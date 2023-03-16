package com.hoangtm14.spring.constants;

public enum MessageCode {
    E_500("E_500", "Internal server error"),
    E_FILE_REQUIRED("E_FILE_REQUIRED", "File is required"),
    E_BAD_CREDENTIALS("E_BAD_CREDENTIALS", "Bad credentials");

    private final String code;
    private final String message;

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
