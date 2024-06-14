package icu.cyclone.parking.user.dao.jpa.entity

import java.util.UUID

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class ParkingUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val role: String? = null,
    @Column(nullable = false, unique = true)
    val email: String? = null,
    val phoneNumber: String? = null,
    @Column(nullable = false)
    val fullName: String? = null,
    @Column(nullable = false)
    val secret: String? = null,
)
