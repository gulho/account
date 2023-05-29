package ee.gulho.assignment.account.exception;

public class TransactionCreateError extends RuntimeException {
    public TransactionCreateError(String message) {
        super(message);
    }
}
