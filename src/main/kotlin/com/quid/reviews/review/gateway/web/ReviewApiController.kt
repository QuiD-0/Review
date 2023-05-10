package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.usecase.CreateReview
import com.quid.reviews.review.usecase.DeleteReview
import com.quid.reviews.review.usecase.FindReview
import com.quid.reviews.review.usecase.UpdateReview
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/review")
class ReviewApiController(
    private val createReview: CreateReview,
    private val findReview: FindReview,
    private val updateReview: UpdateReview,
    private val deleteReview: DeleteReview
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

    @PutMapping("/{id}")
    fun updateReview(
        @PathVariable(name = "id") id: String,
        @RequestBody request: ReviewUpdateRequest
    ): ReviewResponse = updateReview.update(id, request.title, request.description, request.score).let { ReviewResponse.of(it) }

    @DeleteMapping("/{id}")
    fun deleteReview(@PathVariable(name = "id") id: String) = deleteReview.delete(id)

}