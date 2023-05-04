package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import org.springframework.stereotype.Service

interface CompressImage {

    fun list(imgList: List<String>): List<String>

    @Service
    class CompressImageUseCase: CompressImage {

        override fun list(imgList: List<String>): List<String> = imgList.map {
                ImageProcessor.compress(it)
            }.toList()
    }
}