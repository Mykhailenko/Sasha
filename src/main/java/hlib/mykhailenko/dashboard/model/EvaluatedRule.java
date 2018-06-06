package hlib.mykhailenko.dashboard.model;

import java.io.Serializable;

public interface EvaluatedRule extends Serializable {

    enum STATUS {
        OK, FAILED;
    }

    STATUS getStatus();

    String getMessage();

    String getExtendedMessage();
}
