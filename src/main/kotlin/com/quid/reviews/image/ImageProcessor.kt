package com.quid.reviews.image

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam


class ImageProcessor {
    companion object {
        private const val ORIGIN_PATH = "images\\origin"
        private const val COMPRESSED_PATH = "images\\compressed"

        fun save(image: MultipartFile): String {
            val path = ORIGIN_PATH
            val absolutePath = getAbsolutePath(path)
            val fileName =
                image.originalFilename ?: throw IllegalArgumentException("File name is empty")
            val uuidPath = "image_${UUID.randomUUID()}.${fileName.substringAfterLast('.')}"
            val filePath = "$absolutePath\\$uuidPath"
            val dest = File(filePath)
            image.transferTo(dest)
            return "$path\\$uuidPath"
        }

        private fun getAbsolutePath(path: String): String {
            val uploadDir = File(path)
            if (!uploadDir.exists()) {
                uploadDir.mkdir()
            }
            return uploadDir.absolutePath
        }

        fun compress(imgPath: String): String {
            val path = COMPRESSED_PATH
            val uploadDir = File(path)
            if (!uploadDir.exists()) {
                uploadDir.mkdir()
            }
            val file = File(imgPath)
            val image = ImageIO.read(file)
            val compressedFile = File("$COMPRESSED_PATH\\${file.name}")
            val outputStream: OutputStream = FileOutputStream(compressedFile)
            val imageWriter = ImageIO.getImageWritersByFormatName("jpg").next()
            val imageOutputStream = ImageIO.createImageOutputStream(outputStream)
            imageWriter.output = imageOutputStream
            val imageWriteParam: ImageWriteParam = imageWriter.defaultWriteParam
            imageWriteParam.compressionMode = ImageWriteParam.MODE_EXPLICIT
            imageWriteParam.compressionQuality = 0.5f
            imageWriter.write(null, IIOImage(image, null, null), imageWriteParam)
            outputStream.close()
            imageOutputStream.close()
            imageWriter.dispose()
            return "$path\\${file.name}"
        }

        fun viewImage(path: String): ResponseEntity<Resource> {
            var resource = FileSystemResource(path)
            if (!resource.exists()) {
                resource = FileSystemResource("images\\default.jpg")
            }
            val header = HttpHeaders()
            val filePath = Paths.get(path)
            header.add("Content-Type", Files.probeContentType(filePath))
            return ResponseEntity<Resource>(resource, header, HttpStatus.OK)
        }
    }

}

