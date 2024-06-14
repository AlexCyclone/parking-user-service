package icu.cyclone.parking.user.dao.jpa.transformer

import java.util.UUID

import icu.cyclone.parking.user.dao.jpa.entity.ParkingUserEntity
import icu.cyclone.parking.user.dao.jpa.entity.VehicleEntity
import icu.cyclone.parking.user.model.VehicleFilter
import org.springframework.data.jpa.domain.Specification

object VehicleFilterEntityTransformer {
    fun VehicleFilter.toUsersVehiclesSpecification(userId: UUID): Specification<VehicleEntity> =
        Specification.where<VehicleEntity?> { root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get<ParkingUserEntity>("user").get<UUID>("id"), userId)
        }.addLicensePlateCriteria(licencePlate)

    private fun Specification<VehicleEntity>.addLicensePlateCriteria(licencePlate: String?): Specification<VehicleEntity> =
        licencePlate?.takeIf { it.isNotBlank() }?.let {
            and { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("licencePlate"), it)
            }
        } ?: this
}
