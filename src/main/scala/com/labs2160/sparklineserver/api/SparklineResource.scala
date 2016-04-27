package com.labs2160.sparklineserver.api

import javax.ws.rs.{Produces, GET, Path}

import com.typesafe.scalalogging.LazyLogging

/**
 * Created by mdometita on 4/26/16.
 */
@Path("/")
class SparklineResource  extends LazyLogging {

    @GET
    @Produces(Array("image/svg+xml"))
    def getSvg(): String = {
        "<svg></svg>"
    }
}
