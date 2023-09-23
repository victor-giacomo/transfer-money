package br.com.bank.commonkafka.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransferNotification {
    private String uuid;
    private Long transferId;
    private String email;
    private LocalDateTime moment;
    private NotificationStatus status;
}