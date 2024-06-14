package icu.cyclone.parking.user.service

import java.util.UUID

import icu.cyclone.parking.user.dao.VehicleDao
import icu.cyclone.parking.user.dto.VehicleRequest
import icu.cyclone.parking.user.infrastructure.exception.NotFoundException
import icu.cyclone.parking.user.model.Vehicle
import icu.cyclone.parking.user.model.VehicleFilter
import icu.cyclone.parking.user.service.transformer.VehicleTransformer.toNewVehicle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val vehicleDao: VehicleDao,
) {
    fun addUserVehicle(userId: UUID, vehicleRequest: VehicleRequest): Vehicle = vehicleDao.save(vehicleRequest.toNewVehicle(userId)).also {
        logger.info("Vehicle added: [$it]")
    }

    fun findUserVehicles(userId: UUID, vehicleFilter: VehicleFilter): List<Vehicle> =
        vehicleDao.findUsersVehiclesByFilter(userId, vehicleFilter)

    fun getUserVehicle(userId: UUID, vehicleId: UUID): Vehicle = vehicleDao.getUsersVehicleById(userId, vehicleId)

    fun checkVehicleAvailability(userId: UUID, vehicleId: UUID) {
        if (!vehicleDao.isUsersVehicleExistById(userId, vehicleId)) {
            throw NotFoundException("Vehicle with id [$vehicleId] not found")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
        const val OBJECT_GROUP_VEHICLE = "vehicles"
    }
}
