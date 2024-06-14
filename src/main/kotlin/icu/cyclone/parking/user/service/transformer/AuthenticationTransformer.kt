package icu.cyclone.parking.user.service.transformer

import icu.cyclone.parking.user.dto.LogInRequest
import icu.cyclone.parking.user.dto.SignUpRequest
import icu.cyclone.parking.user.model.ParkingUser
import icu.cyclone.parking.user.model.UserRole
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder

object AuthenticationTransformer {
    fun SignUpRequest.toNewParkingUser(passwordEncoder: PasswordEncoder): ParkingUser = ParkingUser(
        id = null,
        role = UserRole.ROLE_USER,
        email = email,
        phoneNumber = phoneNumber,
        fullName = fullName,
        secret = passwordEncoder.encode(secret),
    )

    fun LogInRequest.toUsernamePasswordAuthenticationToken(): UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
        this.email,
        this.secret,
    )
}
