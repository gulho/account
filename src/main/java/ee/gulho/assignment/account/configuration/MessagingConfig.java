package ee.gulho.assignment.account.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String ACCOUNT_TOPIC = "account.create";
    private static final String TRANSACTION_TOPIC = "transaction.create";

    @Bean
    public Queue accountQueue() {
        return new Queue(ACCOUNT_TOPIC, false);
    }
    @Bean
    public Queue transactionQueue() {
        return new Queue(TRANSACTION_TOPIC, false);
    }
}
