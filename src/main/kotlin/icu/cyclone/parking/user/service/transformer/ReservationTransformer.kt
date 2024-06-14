package icu.cyclone.parking.user.service.transformer

import com.fasterxml.jackson.databind.ObjectMapper
import icu.cyclone.parking.user.dto.VehicleRequest
import icu.cyclone.parking.user.model.Reservation
import icu.cyclone.parking.user.model.VehicleFilter

object ReservationTransformer {
    fun Reservation.toVehicleFilter(): VehicleFilter = VehicleFilter(
        licencePlate = vehicleLicencePlate,
    )

    fun Reservation.toVehicleRequest(): VehicleRequest = VehicleRequest(
        licencePlate = vehicleLicencePlate,
    )

    fun String.toReservation(mapper: ObjectMapper): Reservation = mapper.readValue(this, Reservation::class.java)
}
