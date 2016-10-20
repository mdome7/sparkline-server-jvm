package com.labs2160.sparklineserver.core

import java.io.{OutputStream, InputStream}

/**
 * Generates sparklines from parameters.
 */
trait SparklineGenerator {

    def generate(values: Seq[Double], width: Int, height: Int): InputStream

    def writeToStream(outputStream: OutputStream, values: Seq[Double], width: Int, height: Int): Unit
}
