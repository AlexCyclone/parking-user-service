package icu.cyclone.parking.user.dto

import java.net.URL
import java.util.UUID

data class ImageDto(
    val id: UUID,
    val imageURL: URL,
)
