package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "account.create")
    public void receiveAccount(Account account){
        logger.info("Revived {}", account);
    }
    @RabbitListener(queues = "transaction.create")
    public void receiveAccount(Transaction transaction){
        logger.info("Revived {}", transaction);
    }
}
