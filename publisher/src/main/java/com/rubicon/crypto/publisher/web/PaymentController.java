package com.rubicon.crypto.publisher.web;

import com.rubicon.crypto.monitoring.ChannelState;
import com.rubicon.crypto.publisher.service.PaymentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public void pay(@RequestBody ChannelState channelState) {
        paymentService.pay(channelState);
    }
}
