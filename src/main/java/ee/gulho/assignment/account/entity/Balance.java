package ee.gulho.assignment.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Balance implements Serializable {
    private Integer id;
    private BigDecimal amount;
    private String currency;
}
