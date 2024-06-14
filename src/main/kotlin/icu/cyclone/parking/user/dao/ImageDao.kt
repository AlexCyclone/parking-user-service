package icu.cyclone.parking.user.dao

import java.util.UUID

import icu.cyclone.parking.user.model.Image

interface ImageDao {
    fun getByIdAndVehicleId(imageId: UUID, vehicleId: UUID): Image

    fun save(image: Image): Image

    fun delete(image: Image)
}
