package com.quid.reviews.image.gateway.web

import com.quid.reviews.image.usecase.DownloadImage
import com.quid.reviews.image.usecase.ViewImage
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/images")
class ImageController(
    private val viewImage: ViewImage,
    private val downloadImage: DownloadImage
) {

    @GetMapping("/{path}/{fileName}")
    fun getCompressedImageByName(
        @PathVariable("path") path: String, @PathVariable("fileName") fileName: String
    ): ResponseEntity<Resource> = viewImage.find(path, fileName)

    @GetMapping("/{id}")
    fun downloadOriginalImage(@PathVariable("id") id: String, response: HttpServletResponse): Unit =
        downloadImage.byReviewId(id, response)

}