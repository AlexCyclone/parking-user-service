package icu.cyclone.parking.user.service.transformer

import icu.cyclone.parking.user.dto.ImageDto
import icu.cyclone.parking.user.model.Image

object ImageTransformer {
    fun Image.toDto(): ImageDto = ImageDto(
        id = checkNotNull(id) { "Unexpected null value in image.id" },
        imageURL = imageURL,
    )
}
