package hlib.mykhailenko.dashboard.rest;

import hlib.mykhailenko.dashboard.model.EvaluatedRule;
import hlib.mykhailenko.dashboard.model.Ruler;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

@Path("/")
public class RestServer {

    private static final Logger LOGGER = Logger.getLogger(RestServer.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String allURIs() throws Exception {
        return Arrays.stream(RestServer.class.getMethods())
                .filter(x -> x.isAnnotationPresent(Path.class))
                .map(x -> x.getAnnotation(Path.class).value())
                .collect(Collectors.joining("\n"));

    }

    @GET
    @Path("/rules")
    @Produces(MediaType.TEXT_PLAIN)
    public String evaluatedRules() throws Exception {
        return "There will be a set of evaluated rules. " + Ruler.getInstance().doRules();
    }

    @GET
    @Path("/logs")
    @Produces(MediaType.TEXT_PLAIN)
    public String logs() throws Exception {
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(new FileInputStream("./logs/general.log")))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }


}