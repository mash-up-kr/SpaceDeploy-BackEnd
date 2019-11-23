package kr.co.mashup.spacedeploy.spacedeploy.article

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, Int> {
    fun findFirstByUserIdAndYearAndMonthAndDay(userId: Int, year: Int, month: Int, day: Int): ArticleEntity
}