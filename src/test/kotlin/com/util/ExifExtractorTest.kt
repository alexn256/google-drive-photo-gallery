package com.util

import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.time.LocalDateTime

internal class ExifExtractorTest {

    val file = File("src/test/resources/Image.jpg")

    @Test
    fun getExif() {
        val photo = ExifExtractor.getExif(ByteArrayInputStream(FileInputStream(file).readAllBytes()))

        Assert.assertEquals(1200, photo.width)
        Assert.assertEquals(630, photo.height)
        Assert.assertEquals("59° 55' 58.72", photo.latitude)
        Assert.assertEquals("30° 20' 12.46", photo.longitude)
        Assert.assertEquals("xiomi redmi 4x", photo.device)
        Assert.assertEquals(LocalDateTime.of(2020, 2, 16, 17, 50, 21), photo.date)
    }
}