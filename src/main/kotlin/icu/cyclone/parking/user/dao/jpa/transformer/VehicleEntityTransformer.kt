package icu.cyclone.parking.user.dao.jpa.transformer

import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import icu.cyclone.parking.user.dao.jpa.transformer.ImageEntityTransformer.toImage
import icu.cyclone.parking.user.model.Vehicle

object VehicleEntityTransformer {
    fun VehicleEntity.toVehicle(): Vehicle = Vehicle(
        id = id,
        brand = brand,
        model = brand,
        licencePlate = checkNotNull(licencePlate) { "null licencePlate" },
        userId = checkNotNull(user?.id) { "null user" },
        images = images.map { it.toImage() },
    )

    fun Vehicle.toEntity(): VehicleEntity = VehicleEntity(
        id = id,
        brand = brand,
        model = model,
        licencePlate = licencePlate,
        user = ParkingUserEntity(id = userId),
    )
}
