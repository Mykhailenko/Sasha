package hlib.mykhailenko.dashboard.model;

import hlib.mykhailenko.dashboard.util.Properties;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Ruler {

    private static class CacheEntry {
        private Object object;
        private Method method;
        private EvaluatedRule evaluatedRule;
        private Rule rule;
        private Date latestRetrieve;

        public CacheEntry(Object object, Method method) {
            this.object = object;
            this.method = method;
            this.latestRetrieve = null;
            this.rule = method.getAnnotation(Rule.class);
            this.evaluatedRule = null;
        }

        public synchronized EvaluatedRule evaluate() {
            if(latestRetrieve == null || evaluatedRule == null ||
                    Duration.between(
                            Instant.from(latestRetrieve.toInstant()),
                            Instant.now())
                            .toMillis() > rule.updatePeriod()){
                actualyEvaluate();
            }
            assert latestRetrieve != null;
            assert evaluatedRule != null;

            return evaluatedRule;
        }

        private void actualyEvaluate() {
            try {
                evaluatedRule = (EvaluatedRule) method.invoke(object);
                evaluatedRule.setId(rule.id());
                if(evaluatedRule.isOk()){
                    evaluatedRule.setMessage(rule.okMessage());
                } else {
                    evaluatedRule.setMessage(rule.failedMessage());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            latestRetrieve = new Date();
        }
    }

    private static final Logger LOGGER = Logger.getLogger(Ruler.class);

    private static Ruler instance = null;

    public static synchronized Ruler getInstance() throws Exception {
        if(instance == null){
            instance = new Ruler(Properties.PACKAGE.asString());
        }

        return instance;
    };

    private List<CacheEntry> cacheEntries = new LinkedList<>();

    public Ruler(String packageName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections(packageName,
                new SubTypesScanner(false));

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for (Class<?> aClass : allClasses) {
            for (Method aMethod : aClass.getMethods()) {
                if (aMethod.isAnnotationPresent(Rule.class)) {
                    cacheEntries.add(new CacheEntry(aClass, aMethod));
                }
            }
        }
    }

    public List<EvaluatedRule> doRules() throws Exception {
        return cacheEntries
                .stream()
                .map(CacheEntry::evaluate)
                .collect(Collectors.toList());
    }
}
