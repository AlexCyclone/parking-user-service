package icu.cyclone.parking.user.infrastructure.configuration

import icu.cyclone.parking.user.infrastructure.properties.KafkaProperties
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("kafka-mode")
class KafkaConfiguration(
    private val kafkaProperties: KafkaProperties,
) {
    @Bean
    fun reservationTopic(): NewTopic {
        val topic = kafkaProperties.reservationTopic
        return NewTopic(topic.name, topic.partitionsCount, topic.replicationFactor)
    }
}
