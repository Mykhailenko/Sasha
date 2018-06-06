package hlib.mykhailenko.dashboard.util;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class SelfEvictableCache <T> {

    private Date date;

    private int duration; // ms

    private T value;

    public SelfEvictableCache(int duration) {
        this.duration = duration;
    }

    public void put(T value){
        this.value = value;
        this.date = new Date();
    }

    public T get(){
        final long actualDuration = Duration.between(
                Instant.from(date.toInstant()),
                Instant.now())
                .toMillis();
        if(value != null && actualDuration > duration){
            value = null;
        }

        return value;
    }

    public boolean isEmpty(){
        return value == null;
    }

}
