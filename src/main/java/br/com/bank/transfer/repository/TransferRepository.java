package br.com.bank.transfer.repository;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Query(value="select t from Transfer t where sender = :user or receiver = :user order by moment desc")
    List<Transfer> find(@Param("user") User user);
}