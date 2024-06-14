package icu.cyclone.parking.user.storage

import java.net.URL

import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun uploadFile(
        objectGroup: String,
        ownerId: String,
        fileName: String,
        file: MultipartFile,
    ): URL

    fun deleteFile(
        objectGroup: String,
        ownerId: String,
        fileName: String,
    )
}
