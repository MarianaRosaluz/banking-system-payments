package br.rosaluz.banking.system.payment.repository;

import br.rosaluz.banking.system.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
