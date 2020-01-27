package kr.co.mashup.spacedeploy.spacedeploy.article

import kr.co.mashup.spacedeploy.spacedeploy.remind.RemindEntity
import kr.co.mashup.spacedeploy.spacedeploy.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, Int> {
    fun findFirstByUserIdAndYearAndMonthAndDay(userId: Long, year: Int, month: Int, day: Int): ArticleEntity?
    fun findFirstByDailylogId(dailylogId: Long): ArticleEntity?
    fun findAllByRemindId(remindId: Long): Iterable<ArticleEntity>?
    fun findAllByUserIdAndYear(userId: Long, year: Int): Iterable<ArticleEntity>?
}
