package br.rosaluz.banking.system.payment.producer.dto.converter;

import br.rosaluz.banking.system.payment.model.Payment;
import br.rosaluz.banking.system.payment.producer.dto.PaymentMessageDTO;

import java.util.Optional;

public class PaymentToPaymentMessageDTO {

    public static PaymentMessageDTO convert(Payment payment) {
        return Optional.ofNullable(payment).map(paymentDTOChecked -> PaymentMessageDTO.builder()
                .accountOrigin(paymentDTOChecked.getAccountOrigin())
                .codeBar(paymentDTOChecked.getBarCode())
                .value(paymentDTOChecked.getValue())
                .build()).orElse(null);
    }
}
