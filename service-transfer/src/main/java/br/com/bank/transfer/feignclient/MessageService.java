package br.com.bank.transfer.feignclient;

import br.com.bank.transfer.domain.TransferNotification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${message.service.name}", url = "${message.service.url}")
public interface MessageService {
    @PostMapping(value = "/message/transfer-notification/send")
    void send(TransferNotification value);
}
