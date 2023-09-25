package br.com.bank.transfer.service;

import br.com.bank.transfer.domain.NotificationStatus;
import br.com.bank.transfer.domain.TransferNotification;

import java.util.List;

public interface TransferNotificationService {
    List<TransferNotification> list();
    void send(TransferNotification notification);
    void changeStatus(String uuid, NotificationStatus status);
}
