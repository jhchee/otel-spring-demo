package main.github.jhchee.otel.application1;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class HelloService {

    @WithSpan
    public void sleep() throws Exception {
        var parentSpan = Span.current();
        var sleepTime = new Random().nextInt(1000);
        Thread.sleep(sleepTime);
        Span.current().setAttribute("sleepTime", sleepTime);

        log.info("helloName in baggage 1 = {}", Baggage.current().getEntryValue("helloName"));
        throwException();
    }

    public void throwException() {
        throw new RuntimeException("Test exception");
    }
}
