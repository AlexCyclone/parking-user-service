package icu.cyclone.parking.user.service

import java.util.UUID

import icu.cyclone.parking.user.dao.ImageDao
import icu.cyclone.parking.user.model.Image
import icu.cyclone.parking.user.storage.StorageService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class VehicleImageService(
    private val imageDao: ImageDao,
    private val storageService: StorageService,
    private val vehicleService: VehicleService,
) {
    fun uploadImage(
        userId: UUID,
        vehicleId: UUID,
        multipartFile: MultipartFile,
    ): Image {
        val fileName = checkNotNull(multipartFile.originalFilename) { "File name cannot be empty" }
        vehicleService.checkVehicleAvailability(userId, vehicleId)
        val imageUrl =
            storageService.uploadFile(
                objectGroup = OBJECT_GROUP_VEHICLE,
                ownerId = vehicleId.toString(),
                fileName = fileName,
                file = multipartFile,
            )
        return imageDao.save(
            Image(
                fileName = fileName,
                vehicleId = vehicleId,
                imageURL = imageUrl,
            ),
        ).also {
            logger.info("Image added: [$it]")
        }
    }

    fun deleteImage(
        userId: UUID,
        vehicleId: UUID,
        imageId: UUID,
    ) {
        vehicleService.checkVehicleAvailability(userId, vehicleId)
        val image = imageDao.getByIdAndVehicleId(imageId = imageId, vehicleId = vehicleId)
        storageService.deleteFile(
            objectGroup = OBJECT_GROUP_VEHICLE,
            ownerId = vehicleId.toString(),
            fileName = image.fileName,
        )
        imageDao.delete(image)
        logger.info("Image deleted: [$image]")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
        const val OBJECT_GROUP_VEHICLE = "vehicles"
    }
}
