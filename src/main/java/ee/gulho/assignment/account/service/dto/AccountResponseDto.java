package ee.gulho.assignment.account.service.dto;

import java.util.List;
import java.util.UUID;

public record AccountResponseDto(UUID accountId, UUID CustomerId, List<BalanceDto> balances) {
}
