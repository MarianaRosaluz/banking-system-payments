package br.rosaluz.banking.system.payment.producer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMessageDTO {

    private long id;
    private String accountOrigin;
    private String codeBar;
    private Double value;

}
