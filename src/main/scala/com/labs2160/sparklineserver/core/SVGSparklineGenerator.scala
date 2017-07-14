package com.labs2160.sparklineserver.core

import java.io.{ByteArrayInputStream, InputStream, OutputStream}

import com.labs2160.sparklineserver.api.svg.SvgUtil

/**
 * Created by mike on 10/17/16.
 */
class SVGSparklineGenerator extends SparklineGenerator {

    override def generate(values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): InputStream = {
        // TODO: write SVG directly to stream instead of writing complete SVG into String first
        new ByteArrayInputStream(SvgUtil.getSvgString(values, canvasOptions, strokeOptions).getBytes("UTF-8"))
    }

    override def writeToStream(outputStream: OutputStream, values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): Unit = {
        outputStream.write(SvgUtil.getSvgString(values, canvasOptions, strokeOptions).getBytes("UTF-8"))
        outputStream.flush()
    }
}
