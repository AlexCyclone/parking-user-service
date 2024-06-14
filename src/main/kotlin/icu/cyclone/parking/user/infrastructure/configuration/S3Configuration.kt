package icu.cyclone.parking.user.infrastructure.configuration

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import icu.cyclone.parking.user.infrastructure.properties.S3Properties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Configuration(private val properties: S3Properties) {
    @Bean
    fun amazonS3(): AmazonS3 {
        val credentials =
            BasicAWSCredentials(
                properties.access,
                properties.secret,
            )

        return AmazonS3ClientBuilder.standard()
            .withRegion(properties.region)
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .build()
    }
}
