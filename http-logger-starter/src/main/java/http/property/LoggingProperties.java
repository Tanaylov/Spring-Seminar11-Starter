package http.property;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("http.logging")
public class LoggingProperties {

    private boolean logBody = false;
    private Level logLevel = Level.DEBUG;
}
