package br.rosaluz.banking.system.payment.exception;

import org.springframework.http.HttpStatus;

public class TransferNotCompletedExeption  extends BankingSystemAuthenticationException {

    public TransferNotCompletedExeption(String message, String field){
        super(message, field, HttpStatus.NOT_FOUND);
    }
}
