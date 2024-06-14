package icu.cyclone.parking.user.api

import java.util.UUID

import icu.cyclone.parking.user.dto.ImageDto
import icu.cyclone.parking.user.infrastructure.configuration.SecurityConfiguration.Companion.ROLE_USER
import icu.cyclone.parking.user.infrastructure.security.service.AuthInfoService
import icu.cyclone.parking.user.service.VehicleImageService
import icu.cyclone.parking.user.service.transformer.ImageTransformer.toDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/vehicles/{vehicleId}/images")
@Tag(name = "Managing vehicle images")
class VehicleImageController(
    private val vehicleImageService: VehicleImageService,
    private val authInfoService: AuthInfoService,
) {
    @PreAuthorize("hasRole('$ROLE_USER')")
    @PostMapping(headers = ["content-type=multipart/*"])
    @Operation(summary = "", description = "Upload vehicle image")
    fun upload(
        @PathVariable vehicleId: UUID,
        @RequestPart file: MultipartFile,
    ): ImageDto {
        return vehicleImageService.uploadImage(
            userId = authInfoService.getAuthenticatedUserId(),
            vehicleId = vehicleId,
            multipartFile = file,
        ).toDto()
    }

    @PreAuthorize("hasRole('$ROLE_USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "", description = "Remove vehicle image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable vehicleId: UUID,
        @PathVariable id: UUID,
    ) {
        vehicleImageService.deleteImage(
            userId = authInfoService.getAuthenticatedUserId(),
            vehicleId = vehicleId,
            imageId = id,
        )
    }
}
