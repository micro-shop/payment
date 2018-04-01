package cz.microshop.payment.repository;

import cz.microshop.payment.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRepository extends MongoRepository<Payment, String> {

    Payment findByPaymentId(Long paymentId);
}
