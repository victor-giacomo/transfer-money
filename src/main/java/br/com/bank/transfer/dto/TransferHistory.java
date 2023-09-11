package br.com.bank.transfer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TransferHistory {
    private Long id;
    private String moment;
    private String signal;
    private String description;
    private BigDecimal amount;
    private String formatted;

    public void setMoment(LocalDateTime moment) {
        if(moment == null) {
            return;
        }
        this.moment = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm").format(moment);
    }

    public String getFormatted() {
        String formattedAmount = amount != null ? NumberFormat.getCurrencyInstance().format(amount) : "";
        return String.format("%s | %s | %s %s", moment, description, signal, formattedAmount);
    }
}
