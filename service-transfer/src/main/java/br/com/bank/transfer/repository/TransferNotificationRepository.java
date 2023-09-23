package br.com.bank.transfer.repository;

import br.com.bank.transfer.domain.TransferNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferNotificationRepository extends JpaRepository<TransferNotification, String> {
}