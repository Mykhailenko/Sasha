package hlib.mykhailenko.dashboard.util;

import java.util.function.Function;

public class L  {


    public static <T, R> Function<T, R> silent(FunctionThatThrows<T, R> functionThatThrows) {
        return arg -> {
            try {
                return functionThatThrows.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface FunctionThatThrows<T, R> {
        R apply(T var1) throws Exception;

    }


}




