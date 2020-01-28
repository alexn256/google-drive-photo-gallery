package util

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.time.LocalDateTime

internal class ExifExtractorTest {

    val file = File("src/test/resources/Image.jpg")

    @Test
    fun getExif() {
        val extractor = ExifExtractor()
        val photo = extractor.getExif(file)

        Assert.assertEquals("Image", photo.name)
        Assert.assertEquals("jpg", photo.type)
        Assert.assertEquals(1280, photo.width)
        Assert.assertEquals(960, photo.height)
        Assert.assertEquals("48° 21' 55.31", photo.latitude)
        Assert.assertEquals("24° 24' 1.3", photo.longitude)
        Assert.assertEquals("xiaomi redmi 4x", photo.device)
        Assert.assertEquals(LocalDateTime.of(2020, 1, 24, 12, 33, 46), photo.date)
    }
}