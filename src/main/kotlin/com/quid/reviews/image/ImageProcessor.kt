package com.quid.reviews.image

import org.springframework.util.ObjectUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ImageProcessor {
    companion object{
        fun save(imgList: List<MultipartFile>): List<String> {
            val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val path = "images\\$date"
            val uploadDir = File(path)
            if (!uploadDir.exists()) {
                uploadDir.mkdir()
            }
            val absolutePath = uploadDir.absolutePath

            return imgList.filterNot { ObjectUtils.isEmpty(it) }.map {
                val fileName =
                    it.originalFilename ?: throw IllegalArgumentException("File name is empty")
                val uuidPath = "image_${UUID.randomUUID()}.${fileName.substringAfterLast('.')}"
                val filePath = "$absolutePath\\$uuidPath"
                val dest = File(filePath)
                it.transferTo(dest)
                "$uploadDir\\$uuidPath"
            }.toList()
        }
    }

}

