package com.labs2160.sparklineserver

import java.util
import javax.servlet.DispatcherType

import com.typesafe.scalalogging.LazyLogging
import org.eclipse.jetty.server.{Handler, Server}
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.servlet.{ServletHolder, FilterHolder, ServletContextHandler}
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.glassfish.jersey.server.ServerProperties
import org.glassfish.jersey.servlet.ServletContainer

/**
 * Created by mdometita on 4/26/16.
 */
object MainServer extends LazyLogging {

    val DefaultPort = 8080


    def main(args: Array[String]): Unit = {
        logger.info("Starting...")
        val port = if (args.length > 0) args(0).toInt else DefaultPort


        val server = new Server(port)
        val handlers = new HandlerList
        handlers.setHandlers(List(getApiHandler).toArray)
        server.setHandler(handlers)

        try {
            server.start()

            logger.info(s"Listening on ${port}")
            server.join();
        } finally {
            server.destroy()
        }
    }

    def getApiHandler(): Handler = {
        logger.info("Creating handler for REST API")
        val jerseyServlet = new ServletHolder(classOf[ServletContainer]);
        jerseyServlet.setInitOrder(0);

        // manually add jackson json provider packages - for some reason
        // jersey-media-json-jackson does not do its magic on a fat jar
        val scanPackages = "com.labs2160.sparklineserver.api,com.jersey.jaxb,com.fasterxml.jackson.jaxrs.json"
        logger.info("Scanning for REST components under packages: {}", scanPackages);
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, scanPackages);

        val sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
        sch.setContextPath("/")
        sch.addServlet(jerseyServlet, "/api/*");
        addCrossOriginFilter(sch)
        return sch
    }

    def addCrossOriginFilter(handler: ServletContextHandler): Unit = {
        logger.info("Creating cross-origin filter")
        val holder = new FilterHolder(classOf[CrossOriginFilter]);
        holder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        holder.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        holder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        holder.setName("cross-origin");
        handler.addFilter(holder, "*", util.EnumSet.of(DispatcherType.REQUEST))
    }
}
