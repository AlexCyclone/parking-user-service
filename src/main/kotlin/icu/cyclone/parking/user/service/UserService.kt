package icu.cyclone.parking.user.service

import icu.cyclone.parking.user.dao.UserDao
import icu.cyclone.parking.user.model.ParkingUser
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDao: UserDao,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): ParkingUser {
        return username?.takeIf { it.isNotBlank() }
            ?.let {
                userDao.findByEmail(it)
            } ?: throw UsernameNotFoundException("Incorrect email $username")
    }

    fun save(parkingUser: ParkingUser): ParkingUser = userDao.save(parkingUser)
}
