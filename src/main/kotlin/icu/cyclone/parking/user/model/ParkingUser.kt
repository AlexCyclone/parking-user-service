package icu.cyclone.parking.user.model

import java.util.UUID

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class ParkingUser(
    val id: UUID? = null,
    val role: UserRole,
    val email: String,
    val phoneNumber: String?,
    val fullName: String,
    val secret: String,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(role.name))

    override fun getPassword(): String = secret

    override fun getUsername(): String = email
}
