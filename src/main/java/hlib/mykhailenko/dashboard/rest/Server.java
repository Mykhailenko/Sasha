package hlib.mykhailenko.dashboard.rest;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/entry-point")
public class Server {

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        org.eclipse.jetty.server.Server jettyServer = new org.eclipse.jetty.server.Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                Server.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }


        System.out.println("Server has beed started");
    }


    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        System.out.println("ssssssssssssssss");
        return "Test";
    }
}


