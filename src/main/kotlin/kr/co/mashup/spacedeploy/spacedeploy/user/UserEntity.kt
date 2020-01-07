package kr.co.mashup.spacedeploy.spacedeploy.user

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "USER")
data class UserEntity(@Id
                      @GeneratedValue(strategy = GenerationType.IDENTITY)
                      var userId: Long? = null,
                      var uid: String,
                      var email: String? = null,
                      var password: String? = null,
                      var nickname: String? = null,
                      var name: String? = null,
                      var token: String? = null,
                      var pushToken: String? = null,
                      var userCreateTime: LocalDateTime,
                      var userUpdateTime: LocalDateTime) {
    constructor() : this(
            null,
            "temp",
            null,
            null,
            null,
            null,
            null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now())

    constructor(uid: String,
                email: String?,
                password: String?,
                nickname: String?,
                name: String?,
                token: String?,
                pushToken: String?,
                userCreateTime: LocalDateTime,
                userUpdateTime: LocalDateTime) : this(
            null,
            uid,
            email,
            password,
            nickname,
            name,
            token,
            pushToken,
            userCreateTime,
            userUpdateTime)
}
