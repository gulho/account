package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Balance;
import ee.gulho.assignment.account.entity.Transaction;
import ee.gulho.assignment.account.entity.enums.TransactionDirection;
import ee.gulho.assignment.account.exception.AccountNotFoundException;
import ee.gulho.assignment.account.mapper.BalanceRepository;
import ee.gulho.assignment.account.mapper.TransactionRepository;
import ee.gulho.assignment.account.service.dto.TransactionCreateRequest;
import ee.gulho.assignment.account.utils.TransactionValidation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.transaction.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionValidation validator;
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;
    private final MessageSendService sendService;

    @Transactional
    public Transaction createController(TransactionCreateRequest request) {
        var transactionId = UUID.randomUUID();

        var account = accountService.getAccountById(request.getAccountId().toString());
        var balance = getBalanceForTransactionCurrency(request,account);
        validator.validate(request, balance);

        var newBalanceAmount = doTransaction(request, transactionId, account, balance);

        var response = createResponse(request, transactionId, newBalanceAmount);
        sendService.sendTransaction(response);

        return response;
    }

    public Transaction getTransaction(String transactionId) {
        try {
            var uid = UUID.fromString(transactionId);
            return transactionRepository.getTransactionById(uid).orElseThrow();
        } catch (Exception ex) {
            throw new AccountNotFoundException("Transaction UUID is incorrect");
        }
    }

    private static Transaction createResponse(TransactionCreateRequest transactionCreate, UUID transactionId, BigDecimal newBalanceAmount) {
        return Transaction.builder()
                .accountId(transactionCreate.getAccountId())
                .transactionId(transactionId)
                .amount(transactionCreate.getAmount())
                .currency(transactionCreate.getCurrency())
                .direction(transactionCreate.getDirection().toString())
                .description(transactionCreate.getDescription())
                .balanceAmount(newBalanceAmount)
                .build();
    }

    private BigDecimal doTransaction(TransactionCreateRequest transactionCreate, UUID transactionId, Account account, Balance balance) {
        var newBalance = calculateNewAmount(balance.getAmount(), transactionCreate.getAmount(), transactionCreate.getDirection());
        balanceRepository.updateBalanceAmount(newBalance, balance.getId());
        transactionRepository.createTransaction(
                transactionId,
                account.getId(),
                transactionCreate.getCurrency(),
                transactionCreate.getAmount(),
                transactionCreate.getDirection().toString(),
                transactionCreate.getDescription()
                );
        return newBalance;
    }

    private BigDecimal calculateNewAmount(BigDecimal balanceAmount, BigDecimal transactionAmount, TransactionDirection direction) {
        if (direction.equals(TransactionDirection.IN)) {
            return balanceAmount.add(transactionAmount);
        } else {
            return balanceAmount.subtract(transactionAmount);
        }
    }

    private static Balance getBalanceForTransactionCurrency(TransactionCreateRequest request, Account account) {
        return account.getBalances().stream()
                .filter(balance -> balance.getCurrency().equals(request.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new TransactionException("Account do not have balance for current currency"));
    }
}
