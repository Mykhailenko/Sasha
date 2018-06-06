package hlib.mykhailenko.dashboard.rest;

import hlib.mykhailenko.dashboard.model.Ruler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


@Path("/")
public class RestServer {


    Ruler ruler = new Ruler("hlib.mykhailenko.dashboard.rules");

    public RestServer() throws Exception {
    }

    @GET
    @Path("rules")
    @Produces(MediaType.TEXT_PLAIN)
    public String evaluatedRules() throws IOException {


        return "There will be a set of evaluated rules.";
    }


}