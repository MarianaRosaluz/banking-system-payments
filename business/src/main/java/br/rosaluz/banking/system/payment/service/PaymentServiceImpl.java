package br.rosaluz.banking.system.payment.service;

import br.rosaluz.banking.system.payment.exception.AccountNotFoundException;
import br.rosaluz.banking.system.payment.exception.TransferNotCompletedExeption;
import br.rosaluz.banking.system.payment.feign.AccountFeignClient;
import br.rosaluz.banking.system.payment.feign.dto.AccountDTO;
import br.rosaluz.banking.system.payment.feign.dto.BalanceDTO;
import br.rosaluz.banking.system.payment.model.Payment;
import br.rosaluz.banking.system.payment.producer.PaymentProducer;
import br.rosaluz.banking.system.payment.repository.PaymentRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentProducer paymentProducer;

    @Autowired
    private AccountFeignClient accountFeignClient;


    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public  void payment(Payment payment){
        makePayment(payment);
        paymentProducer.send(payment);
        save(payment);
    }

    private void makePayment(Payment payment) {

        var accountOrigin = getAccount(payment.getAccountOrigin());

        makeDecreaseBalance(
                BalanceDTO.builder()
                        .accountNumber(payment.getAccountOrigin())
                        .amount(payment.getValue()).build());
    }

    private void  makeDecreaseBalance(BalanceDTO balanceDTO){

        ResponseEntity<?> responseEntity = accountFeignClient.decreaseBalance(balanceDTO);

        if (responseEntity.getStatusCodeValue() != 200)
        {
            throw new TransferNotCompletedExeption("Transfer cannot be  completed", "transfer");
        }
    }

    private AccountDTO getAccount(String accountNumber){

        ResponseEntity<AccountDTO> accountResponseEntity = accountFeignClient.getAccount(accountNumber);
        if (accountResponseEntity.getStatusCodeValue() != 200)
        {
            throw new AccountNotFoundException("Account not Found", "accountNumber");
        }
        return accountResponseEntity.getBody();
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
