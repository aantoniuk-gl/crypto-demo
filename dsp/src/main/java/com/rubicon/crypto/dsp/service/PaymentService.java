package com.rubicon.crypto.dsp.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.rubicon.crypto.dsp.service.microraiden.MicroraidenDSPSender;
import com.rubicon.crypto.monitoring.ChannelState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final NotificationStorage notificationStorage;
    private final ImpressionStorage impressionStorage;
    private final MicroraidenDSPSender microraidenDSPSender;
    private final AdServerService adServerService;

    public PaymentService(
            NotificationStorage notificationStorage,
            ImpressionStorage impressionStorage,
            MicroraidenDSPSender microraidenDSPSender,
            AdServerService adServerService) {
        this.notificationStorage = notificationStorage;
        this.impressionStorage = impressionStorage;
        this.microraidenDSPSender = microraidenDSPSender;
        this.adServerService = adServerService;
    }

    @Scheduled(fixedDelay = 5000)
    public void schedulePayment() {
        Set<String> winNotifications = notificationStorage.getAllWinNotifications();

        List<String> billedImpIds = notificationStorage.getAllBillingNotifications()
                                                       .stream()
                                                       .filter(winNotifications::contains)
                                                       .collect(Collectors.toList());
        billedImpIds.forEach(this::pay);
    }

    private void pay(String impId) {
        Double price = impressionStorage.getImpression(impId);

        double newBalance = microraidenDSPSender.getChannelBalance() + price;
        ChannelState channelState = microraidenDSPSender.getChannelState(newBalance);

        adServerService.pay(channelState);

        microraidenDSPSender.updateChannelBalance(newBalance);

        notificationStorage.removeWinNotification(impId);
        notificationStorage.removeBillingNotification(impId);
    }
}
