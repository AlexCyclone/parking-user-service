package icu.cyclone.parking.user.dao.jpa

import java.util.UUID

import icu.cyclone.parking.user.dao.ImageDao
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import icu.cyclone.parking.user.dao.jpa.repository.ImageRepository
import icu.cyclone.parking.user.dao.jpa.transformer.ImageEntityTransformer.toEntity
import icu.cyclone.parking.user.dao.jpa.transformer.ImageEntityTransformer.toImage
import icu.cyclone.parking.user.infrastructure.exception.ConflictException
import icu.cyclone.parking.user.infrastructure.exception.NotFoundException
import icu.cyclone.parking.user.model.Image
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class JpaImageDao(
    private val imageRepository: ImageRepository,
) : ImageDao {
    override fun save(image: Image): Image = try {
        imageRepository.save(
            image.toEntity(),
        ).toImage()
    } catch (e: DataIntegrityViolationException) {
        throw ConflictException("Image ${image.fileName} already exist", e)
    }

    override fun getByIdAndVehicleId(imageId: UUID, vehicleId: UUID): Image =
        imageRepository.findImageEntityByIdAndVehicle(imageId, VehicleEntity(id = vehicleId)).orElseThrow {
            NotFoundException("Image with id $vehicleId not found")
        }.toImage()

    override fun delete(image: Image) {
        imageRepository.delete(image.toEntity())
    }
}
