package br.com.bank.transfer.controller;

import br.com.bank.transfer.domain.TransferNotification;
import br.com.bank.transfer.service.TransferNotificationService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferNotificationController {

    private TransferNotificationService service;

    public TransferNotificationController(TransferNotificationService service) {
        this.service = service;
    }

    @PatchMapping(path = "/transfer-notification/status")
    public void changeStatus(@RequestBody TransferNotification notification) {
        service.changeStatus(notification.getUuid(), notification.getStatus());
    }

}