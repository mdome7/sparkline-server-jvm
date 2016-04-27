package com.labs2160.sparklineserver.api.svg

/**
 * Get the SVG points for a polyline needed for the sparkline of the given width and height
 *
 * //TODO: sacrificed more loops for readability (reconsider)
 */
object SvgUtil {

    def getSvgPolylinePoints(values: Seq[Double], width: Int, height: Int): Seq[Tuple2[Int,Int]] = {
        if (values.isEmpty) throw new IllegalArgumentException("values cannot be empty")
        if (height < 2) throw new IllegalArgumentException("height must be 2 or greater")

        var h = height - 1 // We want values to range from 0 to (height - 1)
        val scaledValues = scaleY(values, height)
        val points = generatePoints(scaledValues, width)
        points.map { case (x, y) => (math.floor(x).toInt, math.floor(h - y).toInt) }
    }

    /**
     * Scales the values to the specified height and
     * translates them to 0 minimum.
     * @return scaled values
     */
    private def scaleY(values: Seq[Double], height: Int): Seq[Double] = {

        var h = height - 1 // We want values to range from 0 to (height - 1)

        val (min, max) = values.foldLeft((values(0), values(0)))
        { case ((mn, mx), v) => (math.min(mn, v), math.max(mx, v))}

        var diff = max - min
        values.map((y) => {
            if (y == min) 0D else ((y - min) * h / diff)
        })
    }

    /**
     * Stretch the number of values to fit the width.
     * @return x,y values
     */
    private def generatePoints(values: Seq[Double], width: Int): Seq[Tuple2[Int, Double]] = {
        val w = width - 1 // we want x values to range from 0 to (width - 1)

        // FUTURE - support compression
        if (values.length > width) throw new IllegalArgumentException("horizontal compression not supported - width cannot be less than the number of values")

        var i = -1
        values.map( v => {
            i += 1
            ((i * w) / (values.length - 1), v)
        })
    }
}
