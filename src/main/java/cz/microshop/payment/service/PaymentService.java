package cz.microshop.payment.service;

import cz.microshop.payment.model.Payment;
import cz.microshop.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment) {
        if (payment.getPaymentId() == null) {
            payment.setPaymentId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        }
        return paymentRepository.save(payment);
    }
}
