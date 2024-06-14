package icu.cyclone.parking.user.dao.jpa

import icu.cyclone.parking.user.dao.UserDao
import icu.cyclone.parking.user.dao.jpa.repository.ParkingUserRepository
import icu.cyclone.parking.user.dao.jpa.transformer.ParkingUserEntityTransformer.toEntity
import icu.cyclone.parking.user.dao.jpa.transformer.ParkingUserEntityTransformer.toParkingUser
import icu.cyclone.parking.user.infrastructure.exception.ConflictException
import icu.cyclone.parking.user.model.ParkingUser
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class JpaUserDao(
    private val parkingUserRepository: ParkingUserRepository,
) : UserDao {
    override fun save(parkingUser: ParkingUser): ParkingUser = try {
        parkingUserRepository.save(parkingUser.toEntity())
    } catch (e: DataIntegrityViolationException) {
        throw ConflictException("User ${parkingUser.email} already exist")
    }.toParkingUser()

    override fun findByEmail(email: String): ParkingUser? = parkingUserRepository.findByEmail(email)?.toParkingUser()
}
