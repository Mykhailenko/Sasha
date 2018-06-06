package hlib.mykhailenko.dashboard.rest;

import hlib.mykhailenko.dashboard.github.GitHubConnector;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;


@Path("/")
public class RestServer {

    GitHubConnector gitHubClient = new GitHubConnector();

    public RestServer() throws IOException, URISyntaxException {
    }

    @GET
    @Path("github/pulls")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() throws IOException {

        return "Total amount of hours that PRs are open is " + gitHubClient.hoursPROpen();
    }


}