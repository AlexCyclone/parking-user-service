package icu.cyclone.parking.user.dto

data class SignUpRequest(
    val fullName: String,
    val email: String,
    val phoneNumber: String?,
    val secret: String,
)
