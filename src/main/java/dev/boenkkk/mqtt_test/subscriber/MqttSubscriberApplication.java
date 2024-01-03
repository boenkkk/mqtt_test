package dev.boenkkk.mqtt_test.subscriber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriberApplication implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(MqttSubscriberApplication.class);

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Override
    public void run(ApplicationArguments args) {
        MqttSubscriberService mqttSubscriberService = new MqttSubscriberService(brokerUrl);
        mqttSubscriberService.startSubscriber();
    }
}
