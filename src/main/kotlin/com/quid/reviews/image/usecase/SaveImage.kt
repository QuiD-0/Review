package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@FunctionalInterface
interface SaveImage {
    fun list(imgList: List<MultipartFile>): List<String>

    @Service
    class SaveImageUseCase : SaveImage {
        override fun list(imgList: List<MultipartFile>): List<String> =
            imgList.map { ImageProcessor.save(it) }.toList()

    }
}