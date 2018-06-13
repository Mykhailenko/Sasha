package hlib.mykhailenko.dashboard.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {
    String okMessage();

    String failedMessage();

    String id();

    // in ms
    int updatePeriod() default 10000;

    int maxDelay() default 10000;
}
