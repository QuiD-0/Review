package com.quid.reviews.image

import com.quid.reviews.image.ImageProcessor.Companion.compress
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ImageProcessorTest{

    @Test
    fun compressImage() {
        val image = compress("images\\origin\\image_3509b468-8dfb-410d-9591-b67b95f939ea.jpg")
        assertNotNull(image)
    }
}