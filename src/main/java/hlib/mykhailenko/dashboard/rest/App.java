package hlib.mykhailenko.dashboard.rest;

import  org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setWelcomeFiles(new String[]{"index.html"});

        Server jettyServer = new Server(80);
        jettyServer.setHandler(context);




        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/rest/*");  // /*
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                RestServer.class.getCanonicalName());
//        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.fasterxml.jackson.jaxrs");



        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("resourceBase", "src/main/webapp");
        holderPwd.setInitParameter("dirAllowed","true");
        context.addServlet(holderPwd,"/"); // /index.html

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.stop();
            jettyServer.destroy();
        }
    }
}