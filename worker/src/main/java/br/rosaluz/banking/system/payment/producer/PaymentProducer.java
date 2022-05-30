package br.rosaluz.banking.system.payment.producer;

import br.rosaluz.banking.system.payment.model.Payment;
import br.rosaluz.banking.system.payment.producer.dto.PaymentMessageDTO;
import br.rosaluz.banking.system.payment.producer.dto.converter.PaymentToPaymentMessageDTO;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProducer.class);
    private final String topic;
    private final KafkaTemplate<String, PaymentMessageDTO> kafkaTemplate;

    public PaymentProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, PaymentMessageDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Payment payment){
        var paymentMessageDTO = PaymentToPaymentMessageDTO.convert(payment);
        kafkaTemplate.send(topic, paymentMessageDTO).addCallback(
                success -> logger.info("Messagem send" + success.getProducerRecord().value()),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
