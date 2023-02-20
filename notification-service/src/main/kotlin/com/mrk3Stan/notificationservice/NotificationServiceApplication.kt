package com.mrk3Stan.notificationservice

import com.mrk3Stan.notificationservice.event.OrderPlacedEvent
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener

@SpringBootApplication
class NotificationServiceApplication {

    private val log = LoggerFactory.getLogger(NotificationServiceApplication::class.java)

    @KafkaListener(topics = ["notificationTopic"])
    fun handleNotification(orderPlacedEvent: OrderPlacedEvent) {
        // TODO: send out an email notification
        log.info("Received notification for Order - ${orderPlacedEvent.orderNumber}")
    }
}

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}