package icu.cyclone.parking.user.dao.jpa.repository

import java.util.UUID

import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingUserRepository : JpaRepository<ParkingUserEntity, UUID> {
    fun findByEmail(email: String): ParkingUserEntity?
}
