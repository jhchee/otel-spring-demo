package main.github.jhchee.otel.application1.controller;

import github.jhchee.otel.domain.persistence.Message;
import github.jhchee.otel.domain.persistence.MessageRepository;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import main.github.jhchee.otel.application1.Application1;
import main.github.jhchee.otel.application1.HelloService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final MessageRepository messageRepository;
    private final HelloService helloService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/hello")
    public String hello() {
        var message = new Message();
        message.setContent("Hello");
        messageRepository.save(message);
        kafkaTemplate.send("test-otel", "Hello, Kafka!");
        return "Hello, World!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
//        var baggage = Baggage.current().toBuilder().put("helloName", name).build();
//        try (Scope scope = baggage.storeInContext(Context.current()).makeCurrent()) {
            helloService.sleep();
//        }
        kafkaTemplate.send("test-otel", "Hello, " + name + "!");
        var message = new Message();
        message.setContent("Hello " + name);
        messageRepository.save(message);

        return "Hello, " + name + "!";
    }


}
