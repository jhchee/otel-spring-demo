package main.github.jhchee.otel.application1;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HelloService {
    private final Tracer tracer =  GlobalOpenTelemetry.getTracer("Application1.class.getName()");

//    @WithSpan(value = "sleep")

    public void sleep() {
        var parentSpan = Span.current();
        var span = tracer.spanBuilder("hello").setParent(Context.current().with(parentSpan)).startSpan();

        try (var spanContext = span.makeCurrent())  {
            var sleepTime = new Random().nextInt(1000);
            Thread.sleep(sleepTime);
            Span.current().setAttribute("sleepTime", sleepTime);
            System.out.println("helloName in baggage 1 = " + Baggage.current().getEntryValue("helloName"));
        } catch (Exception e) {
//            Span.current().setStatus(StatusCode.ERROR);
        } finally {
//            span.end();
        }
    }
}
