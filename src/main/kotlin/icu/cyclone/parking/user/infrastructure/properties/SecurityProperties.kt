package icu.cyclone.parking.user.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("security")
data class SecurityProperties(
    val openEndpoints: List<String>,
    val jwt: JwtProperties,
)

data class JwtProperties(
    var secretKey: String,
    var expirationTimeSeconds: Long,
)
