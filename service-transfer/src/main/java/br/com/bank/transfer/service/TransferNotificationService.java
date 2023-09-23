package br.com.bank.transfer.service;

import br.com.bank.transfer.domain.NotificationStatus;
import br.com.bank.transfer.domain.TransferNotification;

public interface TransferNotificationService {
    void send(TransferNotification notification);
    void changeStatus(String uuid, NotificationStatus status);
}
