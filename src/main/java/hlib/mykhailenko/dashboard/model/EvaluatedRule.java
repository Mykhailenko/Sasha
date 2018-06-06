package hlib.mykhailenko.dashboard.model;

import java.io.Serializable;

public class EvaluatedRule implements Serializable {

    public enum STATUS {
        OK, FAILED;
    }

    private STATUS status;

    private String message;

    private String extendedMessage;

    public EvaluatedRule() {
    }

    public EvaluatedRule(STATUS status, String message, String extendedMessage) {
        this.status = status;
        this.message = message;
        this.extendedMessage = extendedMessage;
    }

    @Override
    public String toString() {
        return "EvaluatedRule{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", extendedMessage='" + extendedMessage + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtendedMessage() {
        return extendedMessage;
    }

    public void setExtendedMessage(String extendedMessage) {
        this.extendedMessage = extendedMessage;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
