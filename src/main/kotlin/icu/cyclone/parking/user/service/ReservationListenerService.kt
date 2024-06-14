package icu.cyclone.parking.user.service

import com.fasterxml.jackson.databind.ObjectMapper
import icu.cyclone.parking.user.model.Reservation
import icu.cyclone.parking.user.service.transformer.ReservationTransformer.toReservation
import icu.cyclone.parking.user.service.transformer.ReservationTransformer.toVehicleFilter
import icu.cyclone.parking.user.service.transformer.ReservationTransformer.toVehicleRequest
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
@Profile("kafka-mode")
class ReservationListenerService(
    private val vehicleService: VehicleService,
    private val objectMapper: ObjectMapper,
) {
    @KafkaListener(topics = ["\${kafka.reservation-topic.name}"], groupId = "\${kafka.reservation-topic.consumer-group}")
    fun processReservationEvent(message: String?) = message?.runCatching {
        logger.info("Reservation message received: [$message]")
        val reservation = toReservation(objectMapper)
        addUserVehicleIfNotExist(reservation)
    }?.onFailure {
        logger.error(it.message, it)
    }

    private fun addUserVehicleIfNotExist(reservation: Reservation) {
        vehicleService.findUserVehicles(
            reservation.userId,
            reservation.toVehicleFilter(),
        ).firstOrNull()
            ?: vehicleService.addUserVehicle(reservation.userId, reservation.toVehicleRequest())
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }
}
