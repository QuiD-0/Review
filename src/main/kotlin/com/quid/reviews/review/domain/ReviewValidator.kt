package com.quid.reviews.review.domain

class ReviewValidator(
    private val title: String,
    private val description: String,
    private val rating: Int,
    private val imgList: List<String>,
) {
    fun validate() {
        if (rating < 0 || rating > 10) throw IllegalArgumentException("Rating must be between 0 and 10")
        if (title.length > 100) throw IllegalArgumentException("Title must be less than 100 characters")
        if (description.length > 1000) throw IllegalArgumentException("Description must be less than 1000 characters")
        if (imgList.size > 5) throw IllegalArgumentException("Image list must be less than 5")
    }
}