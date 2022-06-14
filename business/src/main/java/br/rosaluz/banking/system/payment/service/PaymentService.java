package br.rosaluz.banking.system.payment.service;


import br.rosaluz.banking.system.payment.model.Payment;

import java.util.Optional;

public interface PaymentService {

     Payment save(Payment payment);
     void payment(Payment paymentDTO);
     Optional<Payment> findById(long paymentId);
}
