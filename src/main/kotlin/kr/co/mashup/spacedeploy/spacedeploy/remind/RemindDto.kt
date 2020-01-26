package kr.co.mashup.spacedeploy.spacedeploy.remind

import java.time.LocalDateTime
import javax.persistence.*

data class SpecificRemindListDto(
        val date: LocalDateTime,
        val remind: ArrayList<RemindListDto>?
)

data class EditRemindDto(
        val remindId: Long
)

data class RemindListDto(
        val remindId: Long,
        val bestEmotion: Int,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val title: String?,
        val command: String?
)

data class RemindDto(
        val remindId: Long,
        val emotionRank: ArrayList<EmotionCount>?,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val title: String?,
        val command: String?
)

data class EmotionCount(
        val emotion: Int,
        val count: Int
)

data class PostRemindDto(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val title: String?,
        val command: String?
)

@Entity
@Table(name = "REMIND")
data class RemindEntity(@Id
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
