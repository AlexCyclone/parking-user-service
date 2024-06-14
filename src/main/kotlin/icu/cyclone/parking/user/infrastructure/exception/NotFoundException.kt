package icu.cyclone.parking.user.infrastructure.exception

class NotFoundException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
