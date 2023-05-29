package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMessageService implements MessageSendService {

    private final RabbitTemplate template;

    public void sendAccountCreate(Account account) {
        template.convertAndSend("account.create", account);
    }

    @Override
    public void sendTransaction(Transaction transaction) {
        template.convertAndSend("transaction.create", transaction);
    }
}
