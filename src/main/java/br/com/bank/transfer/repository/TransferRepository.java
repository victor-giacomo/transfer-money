package br.com.bank.transfer.repository;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findBySender(User sender);
    List<Transfer> findByReceiver(User receiver);
}