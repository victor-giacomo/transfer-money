package br.com.bank.transfer.service;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.dto.TransferHistory;

import java.util.List;

public interface TransferService {
    void transfer(Transfer transfer);
    List<TransferHistory> history(Long userId);
}
