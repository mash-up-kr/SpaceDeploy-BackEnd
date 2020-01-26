package kr.co.mashup.spacedeploy.spacedeploy.article


import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "DAILYLOG")
data class ArticleEntity(@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
                         var dailylogId: Long? = null,
                         var article: String? = null,
                         var emotion: Int,
                         var year: Int,
                         var month: Int,
                         var day: Int,
                         var dailylogDate: LocalDateTime,
                         var dailylogCreateTime: LocalDateTime,
                         var dailylogUpdateTime: LocalDateTime,
                         var userId: Long,
                         var remindId: Long) {
    constructor() : this(
            null,
            null,
            0,1,0,0,LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),0,0)

    constructor(article: String?,
                emotion: Int,
                year: Int,
                month: Int,
                day: Int,
                dailylogDate: LocalDateTime,
                dailylogCreateTime: LocalDateTime,
                dailylogUpdateTime: LocalDateTime,
                userId: Long,
                remindId: Long) : this(
            null,
            article,
            emotion,
            year,
            month,
            day,
            dailylogDate,
            dailylogCreateTime,
            dailylogUpdateTime,
            userId,
            remindId)
}
