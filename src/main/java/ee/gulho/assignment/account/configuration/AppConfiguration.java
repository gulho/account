package ee.gulho.assignment.account.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@Getter
//@Setter
@PropertySource("classpath:customConfiguration.yml")
public class AppConfiguration {

    @Value("${currencies}")
    List<String> currencies;
}
