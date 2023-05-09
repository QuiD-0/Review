package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@FunctionalInterface
interface ViewImage {
    fun find(fileName: String): ResponseEntity<Resource>

    @Service
    class ViewImageUseCase : ViewImage {
        override fun find(fileName: String): ResponseEntity<Resource> =
            ImageProcessor.viewImage("images\\compressed\\$fileName")
    }
}