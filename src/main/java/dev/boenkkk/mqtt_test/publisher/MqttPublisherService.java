package dev.boenkkk.mqtt_test.publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    private static final Logger logger = LogManager.getLogger(MqttPublisherService.class);

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    public void publishMessage(String message) {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, MqttClient.generateClientId());
            mqttClient.connect();

            String topic = "datapoint";
            mqttClient.publish(topic, message.getBytes(), 0, false);

            mqttClient.disconnect();
        } catch (MqttException e) {
            logger.error(e.getMessage());
        }
    }
}
