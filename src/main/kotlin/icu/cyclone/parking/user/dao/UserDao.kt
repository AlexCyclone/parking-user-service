package icu.cyclone.parking.user.dao

import icu.cyclone.parking.user.model.ParkingUser

interface UserDao {
    fun findByEmail(email: String): ParkingUser?

    fun save(parkingUser: ParkingUser): ParkingUser
}
