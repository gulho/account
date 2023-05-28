package ee.gulho.assignment.account.service;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Transaction;

public interface MessageSendService {
    void sendAccountCreate(Account account);
    void sendTransaction(Transaction transaction);
}
