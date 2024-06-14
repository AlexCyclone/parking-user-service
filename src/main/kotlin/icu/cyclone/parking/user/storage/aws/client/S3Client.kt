package icu.cyclone.parking.user.storage.aws.client

import java.io.BufferedInputStream
import java.net.URL

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import icu.cyclone.parking.user.infrastructure.properties.S3Properties
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3Client(
    private val amazonS3: AmazonS3,
    private val properties: S3Properties,
) {
    fun uploadObject(fileId: String, file: MultipartFile): URL {
        val meta =
            ObjectMetadata().apply {
                contentLength = file.size
            }
        BufferedInputStream(file.inputStream).use {
            amazonS3.putObject(
                PutObjectRequest(properties.bucket, fileId, it, meta)
                    .withCannedAcl(CannedAccessControlList.PublicRead),
            )
        }
        return amazonS3.getUrl(properties.bucket, fileId)
    }

    fun deleteObject(fileId: String) = runCatching {
        amazonS3.deleteObject(
            DeleteObjectRequest(
                properties.bucket,
                fileId,
            ),
        )
    }.onFailure {
        throw RuntimeException(it.message, it)
    }
}
