package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.usecase.CreateReview
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val createReview: CreateReview
) {

    @PostMapping
    fun createReview(request: ReviewCreateRequest): ReviewResponse =
        createReview.create(request).let { ReviewResponse.of(it) }
}