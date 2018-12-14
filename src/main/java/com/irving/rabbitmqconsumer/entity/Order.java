package com.irving.rabbitmqconsumer.entity;

import java.io.Serializable;

/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: rabbitmqProject2
 * Date: 2018-12-02
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 8348138806681849056L;

    private String id;

    private String name;

    //存储消息发送的位移标示
    private String messageId;

    public Order() {
    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
