package kr.co.mashup.spacedeploy.spacedeploy.article

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getArticle(userId: Long, year: Int, month: Int, day: Int): ArticleDto {
        val entity = articleRepository.findFirstByUserIdAndYearAndMonthAndDay(userId, year, month, day)
        return ArticleDto(entity.dailylogId!!, entity.emotion, entity.dailylogUpdateTime, entity.article)
    }

    fun postArticle(resDto: PostArticleDto) {
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(resDto.time.atZone(ZoneId.of("Asia/Seoul")).toInstant())
        val year = calendar.get(Calendar.YEAR)
        val month = (calendar.get(Calendar.MONTH) + 1) % 13
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val article = ArticleEntity(resDto.article, resDto.emotion, year, month, day, resDto.time, LocalDateTime.now(), LocalDateTime.now(), resDto.userId)
        articleRepository.save(article)
    }

    fun deleteArticle(dailylogId: Long) {
        val entity = articleRepository.findFirstByDailylogId(dailylogId = dailylogId)
        articleRepository.delete(entity)
    }

    fun editArticle(articleDto: ArticleDto): ArticleDto {
        val result = articleRepository.findFirstByDailylogId(dailylogId = articleDto.dailylogId)
        result.emotion = articleDto.emotion
        result.article = articleDto.article
        result.dailyLogDate = articleDto.time
        result.dailylogUpdateTime = LocalDateTime.now()
        val entity = articleRepository.save(result)
        val resDto = ArticleDto(entity.dailylogId!!, entity.emotion, entity.dailyLogDate, entity.article)
        return resDto
    }
}