package kr.co.mashup.spacedeploy.spacedeploy.article

import java.time.LocalDateTime

data class ArticleDto(
        val dailylogId: Long,
        val emotion: Int,
        val time: LocalDateTime,
        val article: String?
)

data class PostArticleDto(
        val userId: Long,
        val emotion: Int,
        val time: LocalDateTime,
        val article: String?
)