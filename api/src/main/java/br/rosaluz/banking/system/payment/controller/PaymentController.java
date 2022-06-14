package br.rosaluz.banking.system.payment.controller;

import br.rosaluz.banking.system.payment.dto.PaymentDTO;
import br.rosaluz.banking.system.payment.dto.PaymentResponseDTO;
import br.rosaluz.banking.system.payment.model.Payment;
import br.rosaluz.banking.system.payment.service.PaymentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/banking/system/payment", produces="application/json")
@Api(value="API REST Banking System")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    private final ConversionService conversionService;


    @PostMapping()
    public ResponseEntity<?> payment(@RequestBody @Valid PaymentDTO paymentDTO )throws Exception {

        var payment = conversionService.convert(paymentDTO, Payment.class);
      paymentService.payment(payment);

         return ResponseEntity.ok().build();

    }
    @GetMapping("status/{paymentId}")
    public ResponseEntity<?> statusPayment(@PathVariable long paymentId){

        Payment payment = paymentService.findById(paymentId).get();
        return ResponseEntity.ok(conversionService.convert(payment, PaymentResponseDTO.class));

    }
}
