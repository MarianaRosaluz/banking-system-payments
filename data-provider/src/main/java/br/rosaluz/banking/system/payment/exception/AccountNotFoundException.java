package br.rosaluz.banking.system.payment.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BankingSystemAuthenticationException {

    public AccountNotFoundException(String message, String field){
        super(message, field, HttpStatus.NOT_FOUND);
    }
}
