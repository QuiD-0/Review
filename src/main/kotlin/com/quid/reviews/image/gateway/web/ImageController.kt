package com.quid.reviews.image.gateway.web

import com.quid.reviews.image.ImageProcessor
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths


@RestController
@RequestMapping("/images")
class ImageController {
    @GetMapping(value = ["/compressed/{fileName}"])
    fun getCompressedImageByName(@PathVariable("fileName") fileName: String): ResponseEntity<Resource> {
        val path = "images\\compressed\\"
        return ImageProcessor.viewImage(path + fileName)
    }

    @GetMapping("/origin/{fileName}")
    fun getOriginImageByName(@PathVariable("fileName") fileName: String): ResponseEntity<Resource> {
        val path = "images\\origin\\"
        return ImageProcessor.viewImage(path + fileName)
    }

}