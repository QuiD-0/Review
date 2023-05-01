package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.usecase.CreateReview
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController (
    private val createReview: CreateReview
){
}