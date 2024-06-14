package icu.cyclone.parking.user.infrastructure.security.service

import java.util.Date
import java.util.UUID

import javax.crypto.SecretKey

import icu.cyclone.parking.user.infrastructure.properties.SecurityProperties
import icu.cyclone.parking.user.model.ParkingUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class JwtService(
    private val securityProperties: SecurityProperties,
) {
    fun generateToken(userDetails: UserDetails): String {
        return generateToken(getExtraClaims(userDetails), userDetails)
    }

    fun isTokenValid(token: String): Boolean {
        return extractUsername(token).isNotBlank() && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token) { claims ->
            claims.subject
        }
    }

    fun extractUserId(token: String): UUID {
        return extractClaim(token) { claims ->
            UUID.fromString(
                claims[CLAIM_SUBJECT_ID] as String,
            )
        }
    }

    fun extractUserRole(token: String): String {
        return extractClaim(token) { claims ->
            claims[CLAIM_ROLE] as String
        }
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    private fun getExtraClaims(userDetails: UserDetails): Map<String?, Any?> = if (userDetails is ParkingUser) {
        mapOf(
            CLAIM_SUBJECT_ID to userDetails.id,
            CLAIM_ROLE to userDetails.role,
        )
    } else {
        emptyMap()
    }

    private fun generateToken(extraClaims: Map<String?, Any?>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, getExpirationTime())
    }

    private fun getExpirationTime(): Long {
        return securityProperties.jwt.expirationTimeSeconds * 1000
    }

    private fun buildToken(
        extraClaims: Map<String?, Any?>,
        userDetails: UserDetails,
        expiration: Long,
    ): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey())
            .compact()
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { it.expiration }
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(securityProperties.jwt.secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        const val CLAIM_SUBJECT_ID = "subjectId"
        const val CLAIM_ROLE = "subjectRole"
    }
}
