package com.vetturno.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {

    private int status;
    private String mensaje;
    private Map<String, String> errores;
    private LocalDateTime timestamp;

    public ApiError(
            int status,
            String mensaje,
            Map<String, String> errores,
            LocalDateTime timestamp) {

        this.status = status;
        this.mensaje = mensaje;
        this.errores = errores;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}