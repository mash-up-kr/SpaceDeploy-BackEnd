package kr.co.mashup.spacedeploy.spacedeploy.remind

import java.time.LocalDateTime
import javax.persistence.*

data class SpecificRemindListDto(
        val remind: List<RemindListDto>
)

data class RemindListDto(
        val remindId: Long,
        val bestEmotion: Int?,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val title: String?,
        val command: String?,
        val isUpdated: Boolean
)

data class RemindDto(
        val remindId: Long,
        val emotionRank: List<EmotionCount?>,
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
        val remindId: Long,
        val title: String?,
        val command: String?
)


data class DefaultRemindDto(
        val remindId: Long
)
@Entity
@Table(name = "REMIND")
data class RemindEntity(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        var remindId: Long? = null,
                        var command: String? = null,
                        var remindCreateTime: LocalDateTime,
                        var remindUpdateTime: LocalDateTime,
                        var title: String? = null,
                        var bestEmotion: Int? = null,
                        var startDate: LocalDateTime,
                        var endDate: LocalDateTime,
                        var userId: Long? = null,
                        var isUpdated: Boolean = true) {
    constructor() : this(
            null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null,
            true)

    constructor(startDate: LocalDateTime,
                endDate: LocalDateTime,
                userId: Long?) : this(
            null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null,
            null,
            startDate,
            endDate,
            userId,
            true
    )
}
