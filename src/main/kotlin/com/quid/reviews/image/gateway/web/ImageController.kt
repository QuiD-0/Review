package com.quid.reviews.image.gateway.web

import com.quid.reviews.image.usecase.DownloadImage
import com.quid.reviews.image.usecase.ViewImage
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/images")
class ImageController(
    private val viewImage: ViewImage,
    private val image: DownloadImage
) {

    @GetMapping("/{fileName}")
    fun getCompressedImageByName(@PathVariable("fileName") fileName: String
    ): ResponseEntity<Resource> = viewImage.find(fileName)

    @GetMapping("/origin/{id}")
    fun downloadOriginalImage(@PathVariable("id") id: String, response: HttpServletResponse): Unit =
        image.download(id, response)

}