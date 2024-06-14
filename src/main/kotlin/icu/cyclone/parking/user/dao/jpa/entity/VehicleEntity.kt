package icu.cyclone.parking.user.dao.jpa.entity

import java.util.UUID

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "vehicles",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["user_id", "licence_plate"]),
    ],
)
data class VehicleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val brand: String? = null,
    val model: String? = null,
    @Column(name = "licence_plate", nullable = false)
    val licencePlate: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: ParkingUserEntity? = null,
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    val images: List<ImageEntity> = emptyList(),
)
