package br.rosaluz.banking.system.payment.consumer;

import br.rosaluz.banking.system.payment.consumer.dto.PaymentConsumerMessageDTO;
import br.rosaluz.banking.system.payment.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @Value(value = "${topic.name}")
    private String topic;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listenTopicPayment(ConsumerRecord<String, PaymentConsumerMessageDTO> record){
        System.out.println("teste");
        log.info("Received Message " + record.partition());
        log.info("Received Message " + record.value());
    }

}
