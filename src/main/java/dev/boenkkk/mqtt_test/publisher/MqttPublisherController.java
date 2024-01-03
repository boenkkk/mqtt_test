package dev.boenkkk.mqtt_test.publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mqtt")
public class MqttPublisherController {

    private static final Logger logger = LogManager.getLogger(MqttPublisherController.class);

    @Autowired
    private MqttPublisherService mqttPublisherService;

    @PostMapping("/publish")
    public void publishMessage(@RequestBody String message) {
        logger.info(message);
        mqttPublisherService.publishMessage(message);
    }
}
