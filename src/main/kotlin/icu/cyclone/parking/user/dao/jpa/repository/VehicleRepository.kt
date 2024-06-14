package icu.cyclone.parking.user.dao.jpa.repository

import java.util.Optional
import java.util.UUID

import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<VehicleEntity, UUID>, JpaSpecificationExecutor<VehicleEntity> {
    @EntityGraph(attributePaths = ["images"])
    override fun findAll(spec: Specification<VehicleEntity>): List<VehicleEntity>

    @EntityGraph(attributePaths = ["images"])
    fun getByIdAndUser(id: UUID, user: ParkingUserEntity): Optional<VehicleEntity>

    fun existsByIdAndUser(id: UUID, user: ParkingUserEntity): Boolean
}
