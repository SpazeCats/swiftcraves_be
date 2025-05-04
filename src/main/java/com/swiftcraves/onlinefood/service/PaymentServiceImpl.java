package com.swiftcraves.onlinefood.service;

import org.springframework.beans.factory.annotation.Value;
import com.stripe.exception.StripeException;
import com.stripe.Stripe;
import com.stripe.param.checkout.SessionCreateParams;
import com.swiftcraves.onlinefood.model.Order;
import com.swiftcraves.onlinefood.response.PaymentResponse;
import org.springframework.stereotype.Service;
import com.stripe.model.checkout.Session;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse createPaymentLink(Order order) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Build session params
        SessionCreateParams params = SessionCreateParams.builder()
            .setPaymentMethodTypes(java.util.List.of(SessionCreateParams.PaymentMethodType.CARD)) // Add the supported payment methods
            .setMode(SessionCreateParams.Mode.PAYMENT) // Set payment mode
            .setSuccessUrl("http://localhost:3000/payment/success/" + order.getId()) // Success URL after payment
            .setCancelUrl("http://localhost:3000/payment/fail") // Cancel URL if payment fails
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setQuantity(1L) // Quantity of the item
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("usd") // Currency for the payment
                            .setUnitAmount((long) (order.getTotalPrice() * 100)) // Stripe expects the price in cents, so multiply by 100
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("swiftcraves") // Product name
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .build();

        // Create session using Stripe API
        Session session = Session.create(params);

        // Prepare and return the response with the payment URL
        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());

        return res;
    }
}

