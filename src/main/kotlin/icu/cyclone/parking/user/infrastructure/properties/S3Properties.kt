package icu.cyclone.parking.user.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("aws.s3")
data class S3Properties(
    val access: String,
    val secret: String,
    val region: String,
    val bucket: String,
)
