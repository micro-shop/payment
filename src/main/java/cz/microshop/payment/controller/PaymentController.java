package cz.microshop.payment.controller;

import cz.microshop.payment.model.Payment;
import cz.microshop.payment.model.PaymentSuccess;
import cz.microshop.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<Payment>> findAll()   {
        return new ResponseEntity<>((ArrayList<Payment>) paymentService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpStatus> process(@RequestBody Payment payment)   {
        paymentService.save(payment);
        PaymentSuccess paymentSuccess = new PaymentSuccess();
        paymentSuccess.setOrderId(payment.getOrderId());
        paymentSuccess.setStatus("ORDER_PAID");
        rabbitTemplate.convertAndSend("rabbit-foo", paymentSuccess);


        //return new ResponseEntity<>(paymentService.save(payment), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
