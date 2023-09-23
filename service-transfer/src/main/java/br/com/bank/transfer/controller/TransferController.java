package br.com.bank.transfer.controller;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.dto.TransferHistory;
import br.com.bank.transfer.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    private TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping(path = "/transfer")
    public void transfer(@RequestBody Transfer transfer) {
        service.transfer(transfer);
    }

    @GetMapping(path = "/transfer/history/{userId}")
    public ResponseEntity<List<TransferHistory>> history(@PathVariable Long userId) {
        return ResponseEntity.ok(service.history(userId));
    }
}