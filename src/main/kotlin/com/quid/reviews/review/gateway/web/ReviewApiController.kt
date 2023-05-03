package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.usecase.CreateReview
import com.quid.reviews.review.usecase.FindReview
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/review")
class ReviewApiController(
    private val createReview: CreateReview,
    private val findReview: FindReview
) {

    @PostMapping
    fun createReview(request: ReviewCreateRequest): ReviewResponse =
        createReview.create(request).let { ReviewResponse.of(it) }

    @GetMapping("/list")
    fun getReviewList(): List<ReviewResponse> =
        findReview.getReviewList().map { ReviewResponse.of(it) }

    @GetMapping("/{id}")
    fun getReview(@PathVariable(name = "id") id: String): ReviewDetailResponse =
        ReviewDetailResponse.of(findReview.getReview(id))
}