package br.com.bank.commonkafka.feignclient;

import br.com.bank.commonkafka.dto.TransferNotification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${transfer.notification.service.name}", url = "${transfer.notification.service.url}")
public interface TransferNotificationService {

    @RequestMapping(value = "/transfer-notification/status",
                    method = RequestMethod.PATCH,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    void changeStatus(@RequestBody TransferNotification notification);
}