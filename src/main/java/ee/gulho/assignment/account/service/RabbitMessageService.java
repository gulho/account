package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Transaction;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageService implements MessageSendService {

    private final RabbitTemplate template;
    private MessageConverter converter;
    private MessageProperties messageProperties;

    @Autowired
    public RabbitMessageService(RabbitTemplate template) {
        this.template = template;
        this.converter = template.getMessageConverter();
        this.messageProperties =  new MessageProperties();
    }

    public void sendAccountCreate(Account account) {
        var message = converter.toMessage(account, messageProperties);
        template.send("account.create", message);
    }

    @Override
    public void sendTransaction(Transaction transaction) {
        var message = converter.toMessage(transaction, messageProperties);
        template.send("transaction.create", message);
    }
}
