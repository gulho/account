package ee.gulho.assignment.account.mapper;

import ee.gulho.assignment.account.entity.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface TransactionRepository {

    @Insert("insert into transaction (id, account_id, currency, amount, direction, description) " +
            "VALUES (#{id}, #{accountId}, #{currency}, #{amount}, #{direction}, #{description})")
    void createTransaction(UUID id, UUID accountId, String currency, BigDecimal amount, String direction, String description);

    @Select("select * from transaction where id = #{id}")
    Optional<Transaction> getTransactionById(UUID id);
}
