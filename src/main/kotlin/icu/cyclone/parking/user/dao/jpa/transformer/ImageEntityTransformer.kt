package icu.cyclone.parking.user.dao.jpa.transformer

import icu.cyclone.parking.user.dao.jpa.entity.ImageEntity
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import icu.cyclone.parking.user.model.Image

object ImageEntityTransformer {
    fun ImageEntity.toImage(): Image = Image(
        id = id,
        fileName = checkNotNull(fileName) { "null fileName" },
        imageURL = checkNotNull(imageURL) { "null imageURL" },
        vehicleId = checkNotNull(vehicle?.id) { "null vehicle" },
    )

    fun Image.toEntity(): ImageEntity = ImageEntity(
        id = id,
        fileName = fileName,
        imageURL = imageURL,
        vehicle = VehicleEntity(id = vehicleId),
    )
}
