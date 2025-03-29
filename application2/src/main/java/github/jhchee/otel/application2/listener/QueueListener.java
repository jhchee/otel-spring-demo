package github.jhchee.otel.application2.listener;

import io.opentelemetry.api.trace.Span;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
class QueueListener {
    @KafkaListener(topics = {"test-otel"}, groupId = "test-otel-application-2")
    public void listen(String message) {
        var currentSpan = Span.current();
        var spanContext = currentSpan.getSpanContext();
        System.out.println("Current span ID:" + spanContext.getSpanId());
        System.out.println("Current trace ID:" + spanContext.getTraceId());
        System.out.println("Received Message in group test-otel:" + message);
    }
}