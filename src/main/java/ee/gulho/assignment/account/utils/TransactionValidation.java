package ee.gulho.assignment.account.utils;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Balance;
import ee.gulho.assignment.account.entity.enums.TransactionDirection;
import ee.gulho.assignment.account.exception.TransactionCreateError;
import ee.gulho.assignment.account.service.dto.TransactionCreateRequest;
import org.apache.ibatis.transaction.TransactionException;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidation {

    public void validate(TransactionCreateRequest request, Balance balance) {
        if (request.getDirection().equals(TransactionDirection.OUT) && balance.getAmount().compareTo(request.getAmount()) < 0) {
            throw new TransactionCreateError("Account balance amount is less that in transaction");
        }
    }
}
