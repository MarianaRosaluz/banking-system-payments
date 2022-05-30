package br.rosaluz.banking.system.payment.dto.converter;

import br.rosaluz.banking.system.payment.dto.PaymentDTO;
import br.rosaluz.banking.system.payment.model.Payment;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class PaymentDTOToPayment implements Converter<PaymentDTO, Payment> {

    @Override
    public Payment convert(PaymentDTO paymentDTO) {
        return Optional.ofNullable(paymentDTO).map(paymentDTOChecked -> Payment.builder()
                .accountOrigin(paymentDTOChecked.getAccountOrigin())
                .barCode(paymentDTOChecked.getBarCode())
                .value(paymentDTOChecked.getValue())
                .build()).orElse(null);
    }

}
