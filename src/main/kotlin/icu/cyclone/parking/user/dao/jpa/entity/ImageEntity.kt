package icu.cyclone.parking.user.dao.jpa.entity

import java.net.URL
import java.util.UUID

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "images",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["vehicle_id", "file_name"]),
    ],
)
data class ImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "file_name", nullable = false)
    val fileName: String? = null,
    @Column(nullable = false)
    val imageURL: URL? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    val vehicle: VehicleEntity? = null,
)
