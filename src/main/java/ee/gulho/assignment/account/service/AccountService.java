package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.exception.AccountNotFoundException;
import ee.gulho.assignment.account.mapper.AccountMapper;
import ee.gulho.assignment.account.mapper.BalanceRepository;
import ee.gulho.assignment.account.service.dto.AccountCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountRepository;
    private final BalanceRepository balanceRepository;
    private final MessageSendService messageService;

    private final BigDecimal balanceCreatedAmount = BigDecimal.ZERO;

    @Transactional
    public Account createAccount(AccountCreateRequestDto request) {
        var uid = UUID.randomUUID();
        accountRepository.insertAccount(uid, request.getCustomerId());
        request.getCurrencies()
                .forEach(currency -> createNewBalance(currency, uid));

        var createdAccount = getAccountById(uid.toString());
        sendCreateMessage(createdAccount);

        return createdAccount;
    }

    public Account getAccountById(String id) {
        try {
            var uid = UUID.fromString(id);
            return getAccountById(uid).orElseThrow();
        } catch (Exception ex) {
            throw new AccountNotFoundException("String UUID is incorrect");
        }
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.getAccountById(id);
    }

    private void sendCreateMessage(Account createdAccount) {
        messageService.sendAccountCreate(createdAccount);
    }

    private void createNewBalance(String currency, UUID accountId) {
        balanceRepository.insertBalance(balanceCreatedAmount, currency, accountId);
    }
}
