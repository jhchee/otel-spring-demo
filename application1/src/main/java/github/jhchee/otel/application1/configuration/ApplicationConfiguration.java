package github.jhchee.otel.application1.configuration;

import github.jhchee.otel.domain.configuration.PostgresConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(PostgresConfig.class)
@Configuration
public class ApplicationConfiguration {
}
