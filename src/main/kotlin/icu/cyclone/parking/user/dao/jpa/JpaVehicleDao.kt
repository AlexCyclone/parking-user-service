package icu.cyclone.parking.user.dao.jpa

import java.util.UUID

import icu.cyclone.parking.user.dao.VehicleDao
import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import icu.cyclone.parking.user.dao.jpa.repository.VehicleRepository
import icu.cyclone.parking.user.dao.jpa.transformer.VehicleEntityTransformer.toEntity
import icu.cyclone.parking.user.dao.jpa.transformer.VehicleEntityTransformer.toVehicle
import icu.cyclone.parking.user.dao.jpa.transformer.VehicleFilterEntityTransformer.toUsersVehiclesSpecification
import icu.cyclone.parking.user.infrastructure.exception.ConflictException
import icu.cyclone.parking.user.infrastructure.exception.NotFoundException
import icu.cyclone.parking.user.model.Vehicle
import icu.cyclone.parking.user.model.VehicleFilter
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class JpaVehicleDao(
    private val vehicleRepository: VehicleRepository,
) : VehicleDao {
    override fun save(vehicle: Vehicle): Vehicle = try {
        vehicleRepository.save(
            vehicle.toEntity(),
        ).toVehicle()
    } catch (e: DataIntegrityViolationException) {
        throw ConflictException("Vehicle with licencePlate ${vehicle.licencePlate} already exist", e)
    }

    override fun findUsersVehiclesByFilter(userId: UUID, vehicleFilter: VehicleFilter): List<Vehicle> = vehicleRepository.findAll(
        vehicleFilter.toUsersVehiclesSpecification(userId),
    ).map { it.toVehicle() }

    override fun getUsersVehicleById(userId: UUID, vehicleId: UUID): Vehicle =
        vehicleRepository.getByIdAndUser(vehicleId, ParkingUserEntity(id = userId)).orElseThrow {
            NotFoundException("Vehicle with id $vehicleId not found")
        }.toVehicle()

    override fun isUsersVehicleExistById(userId: UUID, vehicleId: UUID): Boolean =
        vehicleRepository.existsByIdAndUser(vehicleId, ParkingUserEntity(id = userId))
}
