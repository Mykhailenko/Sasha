package hlib.mykhailenko.dashboard.rest;

import hlib.mykhailenko.dashboard.model.EvaluatedRule;
import hlib.mykhailenko.dashboard.model.Ruler;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


@Path("/")
public class RestServer {

    private static final Logger LOGGER = Logger.getLogger(RestServer.class);

    private Ruler ruler = new Ruler("hlib.mykhailenko.dashboard.rules");

    public RestServer() throws Exception {
    }

    @GET
    @Path("rules")
    @Produces(MediaType.TEXT_PLAIN)
    public String evaluatedRules() throws Exception {

        final List<EvaluatedRule> evaluatedRules = ruler.doRules();
//        LOGGER.info(evaluatedRules);


        return "There will be a set of evaluated rules. " + evaluatedRules;
    }


}