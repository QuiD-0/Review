package com.quid.reviews.image.gateway.web

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
    @GetMapping(value = ["/{date}/{fileName}"])
    fun getItemImageByName(@PathVariable("date") date: String, @PathVariable("fileName") fileName: String): ResponseEntity<Resource> {
        val path = "images\\$date\\"
        val resource = FileSystemResource(path + fileName)
        if (!resource.exists()) {
            throw IllegalAccessError()
        }
        val header = HttpHeaders()
        val filePath = Paths.get(path + fileName)
        header.add("Content-Type", Files.probeContentType(filePath))
        return ResponseEntity<Resource>(resource, header, HttpStatus.OK)

    }
}