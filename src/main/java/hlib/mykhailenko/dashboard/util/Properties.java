package hlib.mykhailenko.dashboard.util;

import java.io.FileInputStream;
import java.io.IOException;

public enum Properties {

    PACKAGE("app.rules-package"),

    FETCH_PERIOD("fetch.period");

    Properties(String key){
        this.key = key;
    }

    private final String key;

    public String asString(){
        return prop.getProperty(key);
    }

    public Integer asInteger(){
        return Integer.parseInt(asString());
    }

    private static java.util.Properties prop;

    static {
        prop = new java.util.Properties();
        try {
            prop.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
