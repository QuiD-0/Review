package com.quid.reviews.image

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.servlet.http.HttpServletResponse


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

        fun compress(imgPath: String): String {
            val path = COMPRESSED_PATH
            getAbsolutePath(path)
            val file = File(imgPath)
            val image = ImageIO.read(file)
            val compressedFile = File("$COMPRESSED_PATH\\${file.name}")
            val outputStream: OutputStream = FileOutputStream(compressedFile)
            val imageOutputStream = ImageIO.createImageOutputStream(outputStream)
            val imageWriter = ImageIO.getImageWritersByFormatName("jpg").next()
                .apply { output = imageOutputStream }
            val imageWriteParam: ImageWriteParam = imageWriter.defaultWriteParam.apply {
                compressionMode = ImageWriteParam.MODE_EXPLICIT
                compressionQuality = 0.5f
            }
            imageWriter.write(null, IIOImage(image, null, null), imageWriteParam)
            outputStream.close()
            imageOutputStream.close()
            imageWriter.dispose()
            return "$path\\${file.name}"
        }

        fun viewImage(path: String): ResponseEntity<Resource> {
            var file = File(path)
            if (!file.exists()) {
                file = File("images\\default.jpg")
            }
            val resource = FileSystemResource(file)
            val header = HttpHeaders()
            val filePath = Paths.get(path)
            HttpHeaders().add("Content-Type", Files.probeContentType(filePath))
            return ResponseEntity<Resource>(resource, header, HttpStatus.OK)
        }

        fun download(path: String, response: HttpServletResponse) {
            response.outputStream.use {
                val image = File(path)
                val imageBytes = image.readBytes()
                response.apply {
                    contentType = MediaType.ALL_VALUE
                    setContentLength(imageBytes.size)
                    setHeader("Content-Disposition", "attachment; filename=" + image.name)
                }
                it.write(imageBytes)
            }
        }

        fun download(paths: List<String>, response: HttpServletResponse) {
            response.apply {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                setHeader("Content-Disposition", "attachment; filename=images.zip")
            }
            ZipOutputStream(response.outputStream).use {
                paths.forEach { imageName ->
                    val imageFile = File(imageName)
                    FileInputStream(imageFile).use { inputStream ->
                        val zipEntry = ZipEntry(imageFile.name)
                        zipEntry.time = imageFile.lastModified()
                        it.putNextEntry(zipEntry)
                        inputStream.copyTo(it)
                        it.closeEntry()
                    }
                }
            }
        }

        private fun getAbsolutePath(path: String): String {
            val uploadDir = File(path)
            if (!uploadDir.exists()) {
                uploadDir.mkdir()
            }
            return uploadDir.absolutePath
        }
    }

}

