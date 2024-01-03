package dev.boenkkk.mqtt_test.subscriber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

public class MqttSubscriberService {

    private static final Logger logger = LogManager.getLogger(MqttSubscriberService.class);

    private final String brokerUrl;

    public MqttSubscriberService(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public void startSubscriber() {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, MqttClient.generateClientId());
            mqttClient.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    logger.error(cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    logger.info("Received message: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    logger.info(token);
                }
            });

            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            mqttClient.connect(connectOptions);

            String topic = "datapoint";
            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            logger.info(e.getMessage());
        }
    }
}
