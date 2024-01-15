package com.payment.controller;

import com.payment.payload.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    //http://localhost:8080/api/payment/charge

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @PostMapping("/charge")
    public String charge(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = stripeSecretKey;

        try {
            PaymentIntent intent = createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency());
            return "Payment successful. PaymentIntent ID: " + intent.getId();
        } catch (StripeException e) {
            return "Payment failed. Error: " + e.getMessage();
        }
    }

    private PaymentIntent createPaymentIntent(long amount, String currency) throws StripeException {
        PaymentIntent intent = PaymentIntent.create(
                new PaymentIntentCreateParams.Builder()
                        .setAmount(amount)
                        .setCurrency(currency)
                        .build()
        );
        return intent;
    }
}
