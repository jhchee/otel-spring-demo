package github.jhchee.otel.demo.application1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class Application1

fun main(args: Array<String>) {
    runApplication<Application1>(*args)
}
