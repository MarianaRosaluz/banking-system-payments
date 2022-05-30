package br.rosaluz.banking.system.payment.service;


import br.rosaluz.banking.system.payment.model.Payment;

import java.util.Optional;

public interface PaymentService {

    public Payment save(Payment payment);
    public  boolean payment(Payment paymentDTO);
    public Optional<Payment> findById(long paymentId);
}
