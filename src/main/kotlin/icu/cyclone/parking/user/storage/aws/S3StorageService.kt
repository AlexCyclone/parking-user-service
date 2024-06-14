package icu.cyclone.parking.user.storage.aws

import java.net.URL

import icu.cyclone.parking.user.storage.StorageService
import icu.cyclone.parking.user.storage.aws.client.S3Client
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class S3StorageService(private val s3Client: S3Client) : StorageService {
    override fun uploadFile(
        objectGroup: String,
        ownerId: String,
        fileName: String,
        file: MultipartFile,
    ): URL = s3Client.uploadObject(
        fileId = getFileId(objectGroup, ownerId, fileName),
        file = file,
    ).also {
        logger.info("File [$fileName] uploaded")
    }

    override fun deleteFile(
        objectGroup: String,
        ownerId: String,
        fileName: String,
    ) {
        s3Client.deleteObject(getFileId(objectGroup, ownerId, fileName))
        logger.info("File [$fileName] deleted")
    }

    private fun getFileId(
        objectGroup: String,
        ownerId: String,
        fileName: String,
    ): String = "$objectGroup/$ownerId/$fileName"

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }
}
