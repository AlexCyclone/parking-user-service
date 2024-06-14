package icu.cyclone.parking.user.dto

data class VehicleRequest(
    val brand: String? = null,
    val model: String? = null,
    val licencePlate: String,
)
