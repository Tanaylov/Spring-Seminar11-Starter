package http.configuration;

import http.logging.LoggingStarter;
import http.property.LoggingProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")
public class HttpLoggingConf {

    @Bean
    LoggingStarter loggingStarter(LoggingProperties loggingProperties) {
        return new LoggingStarter(loggingProperties);
    }

}
