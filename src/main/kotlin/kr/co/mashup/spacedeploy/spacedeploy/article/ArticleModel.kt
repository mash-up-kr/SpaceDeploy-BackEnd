package kr.co.mashup.spacedeploy.spacedeploy.article


import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "DAILYLOG")
data class ArticleEntity(@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
//                         @Column(name = "dailylog_id")
                         var dailylogId: Int? = null,
                         var article: String? = null,
                         var emotion: Int,
                         var year: Int,
                         var month: Int,
                         var day: Int,
//                         @Column(name = "dailylog_create_time")
                         var dailylogCreateTime: LocalDateTime,
//                         @Column(name = "dailylog_update_time")
                         var dailylogUpdateTime: LocalDateTime,
//                         @Column(name = "user_id")
                         var userId: Int) {
    constructor() : this(
            null,
            null,
            0,1,0,0,LocalDateTime.now(), LocalDateTime.now(),0)

    constructor(article: String?,
                emotion: Int,
                year: Int,
                month: Int,
                day: Int,
                dailylogCreateTime: LocalDateTime,
                dailylogUpdateTime: LocalDateTime,
                userId: Int) : this(
            null,
            article,
            emotion,
            year,
            month,
            day,
            dailylogCreateTime,
            dailylogUpdateTime,
            userId)
}

data class ResArticleDto(
        val id: Int,
        val emotion: Int,
        val time: LocalDateTime,
        val article: String?
)

data class ResPostArticleDto(
        val userId: Int,
        val emotion: Int,
        val time: LocalDateTime,
        val article: String?
)