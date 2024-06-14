package icu.cyclone.parking.user.model

import java.net.URL
import java.util.UUID

data class Image(
    val id: UUID? = null,
    val fileName: String,
    val imageURL: URL,
    val vehicleId: UUID,
)
