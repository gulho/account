package ee.gulho.assignment.account.exception;

import java.util.Set;

public class TransactionCreateError extends RuntimeException {
    public TransactionCreateError(String message) {
        super(message);
    }

//    public TransactionCreateError(Set<String> validationErrors) {
//        var message = new StringBuilder();
//        validationErrors.forEach(error -> message.append(error + ","));
////        TransactionCreateError(message.toString());
////        TransactionCreateError.
//    }
}
