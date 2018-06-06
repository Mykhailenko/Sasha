package hlib.mykhailenko.dashboard.model;

import hlib.mykhailenko.dashboard.util.SelfEvictableCache;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Ruler {

    private static final Logger LOGGER = Logger.getLogger(Ruler.class);


    private List<Object> objectWithRules = new LinkedList<>();

    public Ruler(String packageName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections(packageName,
                new SubTypesScanner(false));


        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for (Class<?> aClass : allClasses) {
            boolean shouldCreateInstance = false;
            for (Method method : aClass.getMethods()) {
                if (method.isAnnotationPresent(Rule.class)) {
                    shouldCreateInstance = true;
                    break;
                }
            }
            if (shouldCreateInstance) {
                LOGGER.info("Found " + aClass.getCanonicalName());
                objectWithRules.add(aClass.getConstructor().newInstance());
            }

        }
    }

    private SelfEvictableCache<List<EvaluatedRule>> cache = new SelfEvictableCache<>(10000);

    public List<EvaluatedRule> doRules() throws Exception {
        if(cache.isEmpty()) {
            List<EvaluatedRule> result = new LinkedList<>();

            for (Object objectWithRule : objectWithRules) {
                for (Method method : objectWithRule.getClass().getMethods()) {
                    if (method.isAnnotationPresent(Rule.class)) {
                        final EvaluatedRule evaluatedRule = (EvaluatedRule)
                                method.invoke(objectWithRule);
                        result.add(evaluatedRule);
                    }
                }

            }
            cache.put(result);
        }

        return cache.get();
    }

    public static void main(String[] args) throws Exception {
        new Ruler("hlib.mykhailenko.dashboard.rules");

    }
}
