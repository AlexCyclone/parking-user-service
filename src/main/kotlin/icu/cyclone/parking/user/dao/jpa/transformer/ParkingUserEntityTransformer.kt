package icu.cyclone.parking.user.dao.jpa.transformer

import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import icu.cyclone.parking.user.model.ParkingUser
import icu.cyclone.parking.user.model.UserRole

object ParkingUserEntityTransformer {
    fun ParkingUserEntity.toParkingUser(): ParkingUser = ParkingUser(
        id = id,
        role = checkNotNull(role) { "null role" }.toUserRole(),
        email = checkNotNull(email) { "null email" },
        phoneNumber = phoneNumber,
        fullName = checkNotNull(fullName) { "null fullName" },
        secret = checkNotNull(secret) { "null secret" },
    )

    fun ParkingUser.toEntity(): ParkingUserEntity = ParkingUserEntity(
        id = id,
        role = role.name,
        email = email,
        phoneNumber = phoneNumber,
        fullName = fullName,
        secret = secret,
    )

    private fun String.toUserRole(): UserRole = UserRole.valueOf(this)
}
