package hlib.mykhailenko.dashboard.model;

import java.io.Serializable;

public class EvaluatedRule implements Serializable {

    public enum STATUS {
        OK, FAILED;
    }

    private STATUS status;

    private String failMessage;

    private String extendedFailMessage;

    private String okMessage;

    public EvaluatedRule(STATUS status) {
        this.status = status;
    }

    public EvaluatedRule(STATUS status, String failMessage, String extendedFailMessage) {
        this.failMessage = failMessage;
        this.extendedFailMessage = extendedFailMessage;
        this.status = status;
    }

    @Override
    public String toString() {
        return "EvaluatedRule{" +
                "status=" + status +
                ", failMessage='" + failMessage + '\'' +
                ", extendedFailMessage='" + extendedFailMessage + '\'' +
                ", okMessage='" + okMessage + '\'' +
                '}';
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getExtendedFailMessage() {
        return extendedFailMessage;
    }

    public void setExtendedFailMessage(String extendedFailMessage) {
        this.extendedFailMessage = extendedFailMessage;
    }

    public String getOkMessage() {
        return okMessage;
    }

    public void setOkMessage(String okMessage) {
        this.okMessage = okMessage;
    }
}
