package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Balance;
import ee.gulho.assignment.account.entity.Transaction;
import ee.gulho.assignment.account.entity.enums.TransactionDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMessageServiceTest {

    private final String CURRENCY = "USD";

    @Mock
    private RabbitTemplate template;

    @InjectMocks
    private RabbitMessageService service;


    @Test
    void sendAccount_success() {
        var account = Account.builder()
                .id(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .balances(List.of(Balance.builder()
                        .id(10)
                        .amount(BigDecimal.TEN)
                        .currency(CURRENCY)
                        .build()))
                .build();

        service.sendAccountCreate(account);

        verify(template, times(1))
                .convertAndSend("account.create", account);
    }

    @Test
    void sendTransaction_success() {
        var transaction = Transaction.builder()
                .transactionId(UUID.randomUUID())
                .accountId(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .currency(CURRENCY)
                .direction(TransactionDirection.IN.toString())
                .description("Description")
                .balanceAmount(BigDecimal.valueOf(100))
                .build();

        service.sendTransaction(transaction);

        verify(template, times(1))
                .convertAndSend("transaction.create", transaction);
    }
}