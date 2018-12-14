package com.irving.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irving.rabbitmqconsumer.entity.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: rabbitmq-consumer
 * Date: 2018-12-13
 */
@Component
public class OrderReceiver {
    private static final Logger logger = LoggerFactory.getLogger(OrderReceiver.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", type = "topic"),
            key = "order.*"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers,
                               Channel channel) {
        //消费者操作
        logger.trace("---收到消息，开始消费---");
        logger.trace("order id: " + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
