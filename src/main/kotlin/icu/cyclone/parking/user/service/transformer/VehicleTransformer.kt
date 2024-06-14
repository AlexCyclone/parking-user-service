package icu.cyclone.parking.user.service.transformer

import java.util.UUID

import icu.cyclone.parking.user.dto.VehicleDto
import icu.cyclone.parking.user.dto.VehicleRequest
import icu.cyclone.parking.user.model.Vehicle
import icu.cyclone.parking.user.service.transformer.ImageTransformer.toDto

object VehicleTransformer {
    fun VehicleRequest.toNewVehicle(userId: UUID): Vehicle = Vehicle(
        id = null,
        brand = brand,
        model = model,
        licencePlate = licencePlate,
        userId = userId,
    )

    fun Vehicle.toDto(): VehicleDto = VehicleDto(
        id = checkNotNull(id) { "Unexpected null value in vehicle.id" },
        brand = brand,
        model = model,
        licencePlate = licencePlate,
        images = images.map { it.toDto() },
    )
}
