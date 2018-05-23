package com.labs2160.sparklineserver.core

import java.io.{ByteArrayInputStream, OutputStream, InputStream}

import com.labs2160.sparklineserver.api.svg.SvgUtil
import com.labs2160.sparklineserver.core.ImageSparklineGenerator.ImageType
import org.apache.batik.transcoder.{TranscoderOutput, TranscoderInput}
import org.apache.batik.transcoder.image.{JPEGTranscoder, ImageTranscoder, PNGTranscoder}


/**
 * Created by mike on 10/17/16.
 */
class ImageSparklineGenerator(imageType: ImageType) extends SparklineGenerator {

    override def generate(values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): InputStream = {
        throw new NotImplementedError()
    }

    override def writeToStream(os: OutputStream, values: Seq[Double], canvasOptions: CanvasOptions, strokeOptions: StrokeOptions): Unit = {

        val svg = SvgUtil.getSvgString(values, canvasOptions, strokeOptions)
        val is = new ByteArrayInputStream(svg.getBytes("UTF-8"))

        imageType.transcoder.transcode(new TranscoderInput(is), new TranscoderOutput(os))
        os.flush
    }
}

object ImageSparklineGenerator {
    abstract case class ImageType(transcoder: ImageTranscoder)

    object JPEG extends ImageType(prepareJPEGTranscoder)
    object PNG extends ImageType(preparePNGTranscoder)

    private def prepareJPEGTranscoder(): JPEGTranscoder = {
        val t = new JPEGTranscoder()
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.8F)
        t
    }

    private def preparePNGTranscoder() = new PNGTranscoder()
}