package ee.gulho.assignment.account.mapper;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.entity.Balance;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface AccountMapper {

    @Select("select * from account")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "balances", column = "id", javaType = List.class, many = @Many(select = "getAllBalancesByAccount"))
        })
    List<Account> getAllAccounts();

    @Select("select * from account where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "balances", column = "id", javaType = List.class, many = @Many(select = "getAllBalancesByAccount"))
    })
    Optional<Account> getAccountById(UUID id);

    @Select("select * from balance where account_id=#{account_id}")
    List<Balance> getAllBalancesByAccount();

    @Insert("insert into account (id, customer_id) values (#{id}, #{customerId})")
    void insertAccount(UUID id, UUID customerId);

}
