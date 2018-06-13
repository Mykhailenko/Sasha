package hlib.mykhailenko.dashboard.model;


import java.util.Date;

public class EvaluatedRule  {

    public enum Status {
        OK, FAILED;
    }

    private Status status;

    private String id;

    private String message;

    private String extendedMessage;

    private Date date = new Date();

    public static EvaluatedRule makeOk(){
        EvaluatedRule evaluatedRule = new EvaluatedRule();
        evaluatedRule.setStatus(Status.OK);
        return evaluatedRule;
    }

    public static EvaluatedRule makeFailed(String extendedMessage){
        EvaluatedRule evaluatedRule = new EvaluatedRule();
        evaluatedRule.setStatus(Status.FAILED);
        evaluatedRule.setExtendedMessage(extendedMessage);
        return evaluatedRule;
    }

    private EvaluatedRule(){}

    public boolean isOk(){
        return status == Status.OK;
    }

    public boolean isFailed(){
        return status == Status.FAILED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
