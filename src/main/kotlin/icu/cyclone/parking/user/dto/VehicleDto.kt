package icu.cyclone.parking.user.dto

import java.util.UUID

data class VehicleDto(
    val id: UUID,
    val brand: String?,
    val model: String?,
    val licencePlate: String,
    val images: List<ImageDto> = emptyList(),
)
