package hlib.mykhailenko.dashboard.rest;

import hlib.mykhailenko.dashboard.github.GitHubClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;


@Path("/")
public class RestServer {

    GitHubClient gitHubClient = new GitHubClient();

    public RestServer() throws IOException, URISyntaxException {
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() throws IOException {

        return "There are open PR: " + gitHubClient.test();
    }
}