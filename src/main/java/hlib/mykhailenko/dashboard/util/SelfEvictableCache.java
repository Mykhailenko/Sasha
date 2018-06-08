package hlib.mykhailenko.dashboard.util;

import org.apache.log4j.Logger;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class SelfEvictableCache <T> {

    private static final Logger LOGGER = Logger.getLogger(SelfEvictableCache.class);

    private Date date;

    private int duration; // ms

    private T value;

    public SelfEvictableCache(int duration) {
        this.duration = duration;
    }

    public synchronized void put(T value){
        this.value = value;
        this.date = new Date();
    }

    public synchronized T get(){
        final long actualDuration = Duration.between(
                Instant.from(date.toInstant()),
                Instant.now())
                .toMillis();
        if(value != null && actualDuration > duration){
//            LOGGER.info("ActualDuration biggen than 10000ms: " + actualDuration);
            value = null;
        }

        return value;
    }


}
