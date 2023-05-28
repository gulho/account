package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Transaction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @RabbitListener(queues = "account.create")
    public void receiveAccount(Account account){
        System.out.println("Revived" + account);
    }
    @RabbitListener(queues = "transaction.create")
    public void receiveAccount(Transaction transaction){
        System.out.println("Revived" + transaction);
    }
}
