package icu.cyclone.parking.user.dto

data class LogInRequest(
    val email: String,
    val secret: String,
)
