package kr.co.mashup.spacedeploy.spacedeploy.remind

import java.time.LocalDateTime

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
