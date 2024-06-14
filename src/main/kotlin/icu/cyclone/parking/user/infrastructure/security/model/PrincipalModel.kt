package icu.cyclone.parking.user.infrastructure.security.model

import java.util.UUID

data class PrincipalModel(
    val userId: UUID,
    val userEmail: String,
)
