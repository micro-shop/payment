package cz.microshop.payment.model;

/**
 * Created by xnovm on 01.04.2018.
 */
public class PaymentSuccess {

    private Long orderId;
    private String status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
