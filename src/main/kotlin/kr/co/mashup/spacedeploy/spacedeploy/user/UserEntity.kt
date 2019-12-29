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
                      var nicname: String? = null,
                      var name: String? = null,
                      var token: String? = null,
                      var pushToken: String? = null,
                      var userCreateTime: LocalDateTime,
                      var userupdateTime: LocalDateTime) {
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
                nicname: String?,
                name: String?,
                token: String?,
                pushToken: String?,
                userCreateTime: LocalDateTime,
                userupdateTime: LocalDateTime) : this(
            null,
            uid,
            email,
            password,
            nicname,
            name,
            token,
            pushToken,
            userCreateTime,
            userupdateTime)
}
