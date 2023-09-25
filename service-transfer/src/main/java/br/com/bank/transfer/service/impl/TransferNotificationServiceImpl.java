package br.com.bank.transfer.service.impl;

import br.com.bank.transfer.domain.NotificationStatus;
import br.com.bank.transfer.domain.TransferNotification;
import br.com.bank.transfer.exception.NotFoundException;
import br.com.bank.transfer.repository.TransferNotificationRepository;
import br.com.bank.transfer.feignclient.MessageService;
import br.com.bank.transfer.service.TransferNotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferNotificationServiceImpl implements TransferNotificationService {

    private final TransferNotificationRepository repository;
    private final MessageService messageService;

    public TransferNotificationServiceImpl(TransferNotificationRepository repository, MessageService messageService) {
        this.repository = repository;
        this.messageService = messageService;
    }

    @Override
    public List<TransferNotification> list() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void send(TransferNotification notification) {
        notification.setStatus(NotificationStatus.PENDING);
        try {
            messageService.send(notification);
        } catch (Exception e) {
            notification.setStatus(NotificationStatus.ERROR);
            System.out.println(e.getMessage());
        }
        repository.save(notification);
    }

    @Override
    @Transactional
    public void changeStatus(String uuid, NotificationStatus status) {
        TransferNotification notification = repository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
        notification.setStatus(status);
        repository.save(notification);
    }
}