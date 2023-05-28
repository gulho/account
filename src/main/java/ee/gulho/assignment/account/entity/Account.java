package ee.gulho.assignment.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private UUID id;
    private UUID customerId;
    private List<Balance> balances;
}