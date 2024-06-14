package icu.cyclone.parking.user.dao

import java.util.UUID

import icu.cyclone.parking.user.model.Vehicle
import icu.cyclone.parking.user.model.VehicleFilter

interface VehicleDao {
    fun save(vehicle: Vehicle): Vehicle

    fun findUsersVehiclesByFilter(userId: UUID, vehicleFilter: VehicleFilter): List<Vehicle>

    fun getUsersVehicleById(userId: UUID, vehicleId: UUID): Vehicle

    fun isUsersVehicleExistById(userId: UUID, vehicleId: UUID): Boolean
}
