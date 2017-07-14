package com.labs2160.sparklineserver.core

import java.io.{OutputStream, InputStream}

/**
 * Generates sparklines from parameters.
 */
trait SparklineGenerator {

    def generate(values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): InputStream

    def writeToStream(outputStream: OutputStream, values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): Unit
}
