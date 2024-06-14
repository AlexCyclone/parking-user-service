package icu.cyclone.parking.user.infrastructure.exception

class ConflictException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
