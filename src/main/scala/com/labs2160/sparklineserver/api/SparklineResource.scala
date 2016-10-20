package com.labs2160.sparklineserver.api

import java.io.{ByteArrayInputStream, OutputStream}
import javax.ws.rs._
import javax.ws.rs.core.{Response, StreamingOutput}

import com.labs2160.sparklineserver.api.svg.SvgUtil
import com.labs2160.sparklineserver.core.{ImageSparklineGenerator, SVGSparklineGenerator, SparklineGenerator}
import com.typesafe.scalalogging.LazyLogging
import org.apache.batik.transcoder.image.JPEGTranscoder
import org.apache.batik.transcoder.{TranscoderInput, TranscoderOutput}

/**
 * Created by mdometita on 4/26/16.
 */
@Path("/")
class SparklineResource  extends LazyLogging {

    private val svgGenerator = new SVGSparklineGenerator
    private val jpegGenerator = new ImageSparklineGenerator(ImageSparklineGenerator.JPEG)
    private val pngGenerator = new ImageSparklineGenerator(ImageSparklineGenerator.PNG)

    @GET
    @Path("/sparkline.html")
    @Produces(Array("text/html"))
    def getHtml(@QueryParam("values") values: String,
               @QueryParam("w") @DefaultValue("100") width: Int,
               @QueryParam("h") @DefaultValue("20") height: Int) =
        s"<html><body>${SvgUtil.getSvgString(values.split(',').map(_.toDouble), width, height)}</body></html>"

    @GET
    @Path("/sparkline.svg")
    @Produces(Array("image/svg+xml"))
    def getSvg(@QueryParam("values") values: String,
               @QueryParam("w") @DefaultValue("100") width: Int,
               @QueryParam("h") @DefaultValue("20") height: Int): StreamingOutput =
        generateSparkline(svgGenerator, values, width, height)

    @Path("/sparkline.jpg")
    @GET
    @Produces(Array("image/jpeg"))
    def getJpeg(@QueryParam("values") values: String,
                @QueryParam("w") @DefaultValue("100") width: Int,
                @QueryParam("h") @DefaultValue("20") height: Int): StreamingOutput =
        generateSparkline(jpegGenerator, values, width, height)

    @Path("/sparkline.png")
    @GET
    @Produces(Array("image/png"))
    def getPng(@QueryParam("values") values: String,
                @QueryParam("w") @DefaultValue("100") width: Int,
                @QueryParam("h") @DefaultValue("20") height: Int): StreamingOutput =
        generateSparkline(pngGenerator, values, width, height)

    private def generateSparkline(generator: SparklineGenerator, values: String, width: Int, height: Int): StreamingOutput = {
        new StreamingOutput {
            override def write(os: OutputStream): Unit = {
                generator.writeToStream(os, values.split(',').map(_.toDouble), width, height)
            }
        }
    }
}
