package icu.cyclone.parking.user.api

import java.util.UUID

import icu.cyclone.parking.user.dto.VehicleDto
import icu.cyclone.parking.user.dto.VehicleRequest
import icu.cyclone.parking.user.infrastructure.configuration.SecurityConfiguration.Companion.ROLE_USER
import icu.cyclone.parking.user.infrastructure.security.service.AuthInfoService
import icu.cyclone.parking.user.model.VehicleFilter
import icu.cyclone.parking.user.service.VehicleService
import icu.cyclone.parking.user.service.transformer.VehicleTransformer.toDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Managing user vehicles")
class VehicleController(
    private val authInfoService: AuthInfoService,
    private val vehicleService: VehicleService,
) {
    @PreAuthorize("hasRole('$ROLE_USER')")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "", description = "Add new user vehicle")
    fun addUserVehicle(
        @RequestBody vehicleRequest: VehicleRequest,
    ): VehicleDto {
        return vehicleService.addUserVehicle(
            userId = authInfoService.getAuthenticatedUserId(),
            vehicleRequest = vehicleRequest,
        ).toDto()
    }

    @PreAuthorize("hasRole('$ROLE_USER')")
    @GetMapping
    @Operation(summary = "", description = "Find a registered user vehicle that meets the specified criteria")
    fun findUserVehicles(
        @RequestParam licencePlate: String?,
    ): List<VehicleDto> {
        return vehicleService.findUserVehicles(
            userId = authInfoService.getAuthenticatedUserId(),
            vehicleFilter =
                VehicleFilter(
                    licencePlate = licencePlate,
                ),
        ).map { it.toDto() }
    }

    @PreAuthorize("hasRole('$ROLE_USER')")
    @GetMapping("/{vehicleId}")
    @Operation(summary = "", description = "Receiving information about a registered user vehicle")
    fun getUserVehicle(
        @PathVariable vehicleId: UUID,
    ): VehicleDto {
        return vehicleService.getUserVehicle(
            userId = authInfoService.getAuthenticatedUserId(),
            vehicleId = vehicleId,
        ).toDto()
    }
}
