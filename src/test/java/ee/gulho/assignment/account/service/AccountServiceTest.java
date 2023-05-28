package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Balance;
import ee.gulho.assignment.account.exception.AccountNotFoundException;
import ee.gulho.assignment.account.mapper.AccountMapper;
import ee.gulho.assignment.account.mapper.BalanceRepository;
import ee.gulho.assignment.account.service.dto.AccountCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private final String country = "EE";
    private final String eur = "EUR";
    private final String usd = "USD";

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private BalanceRepository balanceRepository;
    @Mock
    private MessageSendService messageService;
    @InjectMocks
    private AccountService service;

    @Test
    void createAccount_accountCreated() {
        var id = UUID.randomUUID();
        var request = new AccountCreateRequestDto(id, country, List.of(eur,usd));
        var account = new Account(UUID.randomUUID(), id, List.of(new Balance(1L, BigDecimal.ZERO, "USD")));
        when(accountMapper.getAccountById(any()))
                .thenReturn(Optional.ofNullable(account));

        var createdAccount = service.createAccount(request);

        assertThat(createdAccount)
                .isNotNull()
                .isEqualTo(account);
    }

    @Test
    void getAccountById_accountGetOk() {
        var uid = UUID.randomUUID();
        var account = new Account(uid, UUID.randomUUID(), List.of(new Balance(1L, BigDecimal.ZERO, "USD")));
        when(accountMapper.getAccountById(any()))
                .thenReturn(Optional.ofNullable(account));

        var gettedAccount = service.getAccountById(uid.toString());

        assertThat(gettedAccount)
                .isNotNull()
                .isEqualTo(account);
    }

    @Test
    void getAccountById_uuidException() {
        var incorrect_uuid = "1234";

        assertThatThrownBy(() -> service.getAccountById(incorrect_uuid))
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    void getAccountById_accountNotFound() {
        var uid = UUID.randomUUID();
        when(accountMapper.getAccountById(uid)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getAccountById(uid.toString()))
                .isInstanceOf(AccountNotFoundException.class);
    }

}