package br.com.bank.transfer.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="transfer_notifications")
@Getter
@Setter
@NoArgsConstructor
public class TransferNotification {

    @Id
    private String uuid;

    @OneToOne
    @JsonIgnore
    private Transfer transfer;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime moment;

    @Column(nullable = false)
    private NotificationStatus status;

    public TransferNotification(Transfer transfer) {
        this.uuid = UUID.randomUUID().toString();
        this.transfer = transfer;
        this.email = transfer.getReceiver().getEmail();
        this.moment = LocalDateTime.now();
    }

    @JsonGetter
    public Long getTransferId() {
        return transfer != null ? transfer.getId() : null;
    }
}