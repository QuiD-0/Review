package com.quid.reviews.image.gateway.message.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

interface ImageProducer {
    fun compress(message: String)

    @Component
    class KafkaImageProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>
    ) : ImageProducer {

        override fun compress(message: String) {
            kafkaTemplate.send("compressImage", message)
        }
    }
}