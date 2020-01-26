package kr.co.mashup.spacedeploy.spacedeploy.article

import kr.co.mashup.spacedeploy.spacedeploy.Header.HeaderDTO
import kr.co.mashup.spacedeploy.spacedeploy.login.LoginRepository
import kr.co.mashup.spacedeploy.spacedeploy.remind.RemindRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Service
class ArticleService(val articleRepository: ArticleRepository, val loginRepository: LoginRepository, val remindRepository: RemindRepository) {
    fun getArticle(year: Int, month: Int, day: Int, header: HeaderDTO): ArticleDto {
        val userId = loginRepository.findFirstByUid(header.uid!!).userId!!
        val entity = articleRepository.findFirstByUserIdAndYearAndMonthAndDay(userId, year, month, day)
        return ArticleDto(entity.dailylogId!!, entity.emotion, entity.dailylogUpdateTime, entity.article)
    }

    fun postArticle(resDto: PostArticleDto, header: HeaderDTO) {
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(resDto.time.atZone(ZoneId.of(header.timeZone)).toInstant())
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        val year = calendar.get(Calendar.YEAR)
        val month = (calendar.get(Calendar.MONTH) + 1) % 13
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.add(Calendar.DATE, -(weekDay - 1)) //etc
        val userId = loginRepository.findFirstByUid(header.uid!!).userId!!
        val remindId: Long = 1//remindRepository
        val article = ArticleEntity(resDto.article, resDto.emotion, year, month, day, resDto.time, LocalDateTime.now(), LocalDateTime.now(), userId, remindId)
        articleRepository.save(article)
    }

    fun deleteArticle(dailylogId: Long, header: HeaderDTO) {
        val entity = articleRepository.findFirstByDailylogId(dailylogId = dailylogId)
        articleRepository.delete(entity)
    }

    fun editArticle(articleDto: ArticleDto, header: HeaderDTO): ArticleDto {
        val result = articleRepository.findFirstByDailylogId(dailylogId = articleDto.dailylogId)
        result.emotion = articleDto.emotion
        result.article = articleDto.article
        result.dailylogDate = articleDto.time
        result.dailylogUpdateTime = LocalDateTime.now()
        val entity = articleRepository.save(result)
        val resDto = ArticleDto(entity.dailylogId!!, entity.emotion, entity.dailylogDate, entity.article)
        return resDto
    }
}