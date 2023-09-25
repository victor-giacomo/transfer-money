package br.com.bank.transfer.controller;

import br.com.bank.transfer.domain.TransferNotification;
import br.com.bank.transfer.service.TransferNotificationService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferNotificationController {

    private TransferNotificationService service;

    public TransferNotificationController(TransferNotificationService service) {
        this.service = service;
    }

    @GetMapping(path = "/transfer-notification")
    public ResponseEntity<List<TransferNotification>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PatchMapping(path = "/transfer-notification/status")
    public void changeStatus(@RequestBody TransferNotification notification) {
        service.changeStatus(notification.getUuid(), notification.getStatus());
    }

}