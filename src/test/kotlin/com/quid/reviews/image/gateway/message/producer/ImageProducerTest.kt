package com.quid.reviews.image.gateway.message.producer

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ImageProducerTest(@Autowired val imageProducer: ImageProducer){

    @Test
    fun producerTest(){
        imageProducer.compress("test")
    }
}