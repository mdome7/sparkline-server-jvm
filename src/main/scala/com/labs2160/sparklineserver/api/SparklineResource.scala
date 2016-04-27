package com.labs2160.sparklineserver.api

import javax.ws.rs._

import com.labs2160.sparklineserver.api.svg.SvgUtil
import com.typesafe.scalalogging.LazyLogging
import scala.collection.JavaConversions._

/**
 * Created by mdometita on 4/26/16.
 */
@Path("/")
class SparklineResource  extends LazyLogging {

    @GET
    @Produces(Array("image/svg+xml"))
    def getSvg(@QueryParam("values") values: String,
               @QueryParam("w") @DefaultValue("100") width: Int,
               @QueryParam("h") @DefaultValue("20") height: Int): String = {

        var points = SvgUtil.getSvgPolylinePoints(values.split(',').map(_.toDouble), width, height)
        var svg = new StringBuilder(s"""<svg width="${width}" height="${height}">""")
        svg.append("""<polyline style="fill: none; stroke: blue; stroke-width: 1" points="""")
        points.foldLeft(svg) { (svg, p) => {svg.append(p._1).append(",").append(p._2).append(" ")}}
        svg.append("""" /> </svg>""")
        svg.toString()
    }

    @GET
    @Produces(Array("text/html"))
    def getHtml(@QueryParam("values") values: String,
               @QueryParam("w") @DefaultValue("100") width: Int,
               @QueryParam("h") @DefaultValue("20") height: Int): String = {

        return "<html><body>" + getSvg(values, width, height) + "</body></html>"
    }

}
