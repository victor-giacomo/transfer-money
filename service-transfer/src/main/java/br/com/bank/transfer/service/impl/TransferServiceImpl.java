package br.com.bank.transfer.service.impl;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.domain.TransferNotification;
import br.com.bank.transfer.domain.User;
import br.com.bank.transfer.dto.TransferHistory;
import br.com.bank.transfer.exception.BusinessException;
import br.com.bank.transfer.repository.TransferRepository;
import br.com.bank.transfer.service.AuthorizationService;
import br.com.bank.transfer.service.TransferNotificationService;
import br.com.bank.transfer.service.TransferService;
import br.com.bank.transfer.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    UserService userService;
    AuthorizationService authorizationService;
    TransferNotificationService notificationService;
    TransferRepository transferRepository;

    @Override
    @Transactional
    public void transfer(Transfer transfer) {
        BigDecimal transferAmount = transfer.getAmount();
        validateTransfer(transferAmount);

        User sender = userService.find(transfer.getSender().getId());
        User receiver = userService.find(transfer.getReceiver().getId());

        transfer.setSender(sender);
        transfer.setReceiver(receiver);
        transfer.setMoment(LocalDateTime.now());

        transferRepository.save(transfer);
        userService.transferAmount(sender, receiver, transferAmount);

        notificationService.send(new TransferNotification(transfer));
    }

    @Override
    public List<TransferHistory> history(Long userId) {
        User user = new User(userId);

        List<Transfer> transfers = transferRepository.find(user);

        return transfers.stream().map(transfer -> {

            TransferHistory transferHistory = new TransferHistory();
            transferHistory.setId(transfer.getId());
            transferHistory.setMoment(transfer.getMoment());
            User sender = transfer.getSender();
            if(sender.getId().equals(userId)) {
                transferHistory.setSignal("-");
                transferHistory.setDescription(transfer.getReceiver().getName());
            } else {
                transferHistory.setSignal("+");
                transferHistory.setDescription(sender.getName());
            }
            transferHistory.setAmount(transfer.getAmount());
            return transferHistory;

        }).collect(Collectors.toList());
    }

    private void validateTransfer(BigDecimal transferAmount) {
        if(transferAmount.signum() <= 0) {
            throw new BusinessException("You should transfer a positive amount");
        }

        if(!authorizationService.authorize()) {
            throw new BusinessException("This transfer is not authorized");
        }
    }

}
