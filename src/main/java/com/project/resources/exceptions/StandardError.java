package com.project.resources.exceptions;

import java.io.Serializable;

// Classe base para representação de erros padrão
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timeStamp;    // Timestamp do erro
    private Integer status;    // Código HTTP do erro
    private String error;      // Tipo do erro
    private String message;    // Mensagem descritiva
    private String path;       // Path da requisição que causou o erro

    public StandardError() {
        super();
    }

    public StandardError(Long timeStamp, Integer status, String error, String message, String path) {
        super();
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // GETTERS E SETTERS
    public Long getTimeStamp() { return timeStamp; }
    public void setTimeStamp(Long timeStamp) { this.timeStamp = timeStamp; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}