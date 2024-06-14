package icu.cyclone.parking.user.service

import icu.cyclone.parking.user.dto.JwtAuthenticationResponse
import icu.cyclone.parking.user.dto.LogInRequest
import icu.cyclone.parking.user.dto.SignUpRequest
import icu.cyclone.parking.user.infrastructure.security.service.JwtService
import icu.cyclone.parking.user.service.transformer.AuthenticationTransformer.toNewParkingUser
import icu.cyclone.parking.user.service.transformer.AuthenticationTransformer.toUsernamePasswordAuthenticationToken
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) {
    fun signUp(request: SignUpRequest): JwtAuthenticationResponse {
        val user = userService.save(request.toNewParkingUser(passwordEncoder))
        return JwtAuthenticationResponse(jwtService.generateToken(user)).also {
            logger.info("User [${user.email}] created")
        }
    }

    fun login(request: LogInRequest): JwtAuthenticationResponse {
        authenticationManager.authenticate(request.toUsernamePasswordAuthenticationToken())
        val user = userService.loadUserByUsername(request.email)
        return JwtAuthenticationResponse(jwtService.generateToken(user))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }
}
