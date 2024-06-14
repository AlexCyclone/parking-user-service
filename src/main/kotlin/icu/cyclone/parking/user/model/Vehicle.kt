package icu.cyclone.parking.user.model

import java.util.UUID

data class Vehicle(
    val id: UUID? = null,
    val brand: String?,
    val model: String?,
    val licencePlate: String,
    val userId: UUID,
    val images: List<Image> = emptyList(),
)
