package com.swiftcraves.onlinefood.service;

import com.stripe.exception.StripeException;
import com.swiftcraves.onlinefood.model.Order;
import com.swiftcraves.onlinefood.response.PaymentResponse;


public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
    
}
