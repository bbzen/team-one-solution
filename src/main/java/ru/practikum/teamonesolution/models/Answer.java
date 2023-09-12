package ru.practikum.teamonesolution.models;

public class Answer {
    String json;
    String message;
    int status;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Password{" +
                "json='" + json + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
