package icu.cyclone.parking.user.dao.jpa.repository

import java.util.Optional
import java.util.UUID

import icu.cyclone.parking.user.dao.jpa.entity.ImageEntity
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<ImageEntity, UUID> {
    fun findImageEntityByIdAndVehicle(id: UUID, vehicleEntity: VehicleEntity): Optional<ImageEntity>
}
