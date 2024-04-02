package dev.yohans.core.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ErrorMessageWrapper {
    private HttpStatus status;
    private String message;
    private Map<String, String> validationMessages;

    public ErrorMessageWrapper(){};
    public ErrorMessageWrapper(HttpStatus status, String message, Map<String, String> validationMessages) {
        this.status = status;
        this.message = message;
        this.validationMessages = validationMessages;
    }

    public ErrorMessageWrapper(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        //validationMessages.put("Campo:","Nenhum erro de validação encontrado");
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getValidationMessages() {
        return validationMessages;
    }
}
