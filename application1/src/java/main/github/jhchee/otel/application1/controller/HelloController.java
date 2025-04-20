package main.github.jhchee.otel.application1.controller;

import github.jhchee.otel.domain.persistence.Message;
import github.jhchee.otel.domain.persistence.MessageRepository;
import lombok.RequiredArgsConstructor;
import main.github.jhchee.otel.application1.HelloService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public String helloName(@PathVariable String name) throws Exception {
        helloService.sleep();
        kafkaTemplate.send("test-otel", "Hello, " + name + "!");
        var message = new Message();
        message.setContent("Hello " + name);
        messageRepository.save(message);

        return "Hello, " + name + "!";
    }
}
