package com.quid.reviews.image.usecase

import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.servlet.http.HttpServletResponse


interface DownloadImage {
    fun byReviewId(id: String, response: HttpServletResponse)

    @Service
    class DownloadImageUseCase(
        private val reviewRepository: ReviewRepository
    ) : DownloadImage {
        override fun byReviewId(id: String, response: HttpServletResponse) {
            val review = reviewRepository.findById(id)
            response.contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE
            response.setHeader("Content-Disposition", "attachment; filename=images.zip")
            val outputStream = response.outputStream
            ZipOutputStream(outputStream).use { zipOutputStream ->
                review.imgList.forEach { imageName ->
                    val imageFile = File(imageName)
                    FileInputStream(imageFile).use { inputStream ->
                        val zipEntry = ZipEntry(imageFile.name)
                        zipEntry.time = imageFile.lastModified()
                        zipOutputStream.putNextEntry(zipEntry)
                        inputStream.copyTo(zipOutputStream)
                        zipOutputStream.closeEntry()
                    }
                }
            }
        }

    }
}