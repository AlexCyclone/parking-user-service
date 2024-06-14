package icu.cyclone.parking.user.infrastructure.exception

class ForbiddenException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
