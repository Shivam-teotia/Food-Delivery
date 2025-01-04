package org.shivam.fooddelivery.service;

import com.stripe.exception.StripeException;
import org.hibernate.service.spi.ServiceException;
import org.shivam.fooddelivery.Model.Order;
import org.shivam.fooddelivery.response.PaymentResponse;

public interface PaymentService {
    public PaymentResponse generatePaymentLink(Order order) throws StripeException;
}
