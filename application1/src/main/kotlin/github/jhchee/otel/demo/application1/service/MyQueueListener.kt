package github.jhchee.otel.demo.application1.service

import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component

@Component
class MyQueueListener {
    @SqsListener("myQueue")
    fun listen(message: String?, @Headers headers: MessageHeaders) {
        println(message)
    }
}