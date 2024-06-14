package icu.cyclone.parking.user.model

import java.util.UUID

data class Reservation(
    val userId: UUID,
    val vehicleLicencePlate: String,
)
