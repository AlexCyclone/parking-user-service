package icu.cyclone.parking.user.api

import icu.cyclone.parking.user.dto.JwtAuthenticationResponse
import icu.cyclone.parking.user.dto.LogInRequest
import icu.cyclone.parking.user.dto.SignUpRequest
import icu.cyclone.parking.user.service.AuthenticationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/auth"])
@Tag(name = "User registration and authentication")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {
    @PostMapping("/sign-up", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "", description = "Registration of a new user")
    fun signUp(
        @RequestBody request: SignUpRequest,
    ): JwtAuthenticationResponse {
        return authenticationService.signUp(request)
    }

    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Login", description = "Authentication of existing users")
    fun login(
        @RequestBody request: LogInRequest,
    ): JwtAuthenticationResponse {
        return authenticationService.login(request)
    }
}
