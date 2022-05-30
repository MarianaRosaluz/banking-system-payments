package br.rosaluz.banking.system.payment.service;

import br.rosaluz.banking.system.payment.model.Payment;
import br.rosaluz.banking.system.payment.producer.PaymentProducer;
import br.rosaluz.banking.system.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentProducer paymentProducer;


    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public  boolean payment(Payment payment){
        paymentProducer.send(payment);
        save(payment);
        return  true;
    }

    @Override
    public Payment save(Payment payment){
        return  paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(long paymentId){
        return paymentRepository.findById(paymentId);
    }

}
