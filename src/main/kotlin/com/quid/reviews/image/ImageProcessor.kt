package com.quid.reviews.image

import org.springframework.util.ObjectUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam


class ImageProcessor {
    companion object{
        fun save(imgList: List<MultipartFile>): List<String> {
            val path = "images\\origin"
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

        fun compress(imgList: List<String>){
            val path = "images\\compressed"
            val uploadDir = File(path)
            if (!uploadDir.exists()) {
                uploadDir.mkdir()
            }
            imgList.forEach(
                fun (img: String){
                    val file = File(img)
                    val image = ImageIO.read(file)
                    val compressedFile = File("images\\compressed\\${file.name}")
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
                }
            )
        }
    }

}

