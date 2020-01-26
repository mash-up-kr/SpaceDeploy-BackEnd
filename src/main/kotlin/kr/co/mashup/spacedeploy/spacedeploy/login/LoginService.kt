package kr.co.mashup.spacedeploy.spacedeploy.login

import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Error.UserException
import kr.co.mashup.spacedeploy.spacedeploy.Header.HeaderDTO
import kr.co.mashup.spacedeploy.spacedeploy.user.UserDto
import kr.co.mashup.spacedeploy.spacedeploy.user.UserEntity
import org.springframework.stereotype.Service

@Service
class LoginService(val loginRepository: LoginRepository) {
    fun login(loginDto: LoginDto): UserDto {
        var userEntity = loginDto.uid?.let { loginRepository.findFirstByUid(it) }
        if (userEntity == null) {
            userEntity = UserEntity()
        }
        userEntity?.pushToken = loginDto.pushToken
        userEntity?.token = loginDto.token
        val saveEntity = loginRepository.save(userEntity)
        val userDto = UserDto(userId = saveEntity.uid, token = saveEntity.token)
        return userDto
    }

    fun logout(header: HeaderDTO) {
        var userEntity = header.uid?.let { loginRepository.findFirstByUid(it) }
        if (userEntity == null) {
            throw UserException(ErrorsDetails(9000, "User not found"))
        }
        userEntity?.pushToken = null
        loginRepository.save(userEntity)
    }
}