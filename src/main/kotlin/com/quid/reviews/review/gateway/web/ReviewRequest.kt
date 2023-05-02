package com.quid.reviews.review.gateway.web

import org.springframework.web.multipart.MultipartFile

data class ReviewCreateRequest(
    val title: String, val description: String, val score: Int, val author: String, val productId: Long, val imgList: List<MultipartFile>
)