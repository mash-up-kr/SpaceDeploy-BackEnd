package kr.co.mashup.spacedeploy.spacedeploy.article

import kr.co.mashup.spacedeploy.spacedeploy.ETC.getEndDate
import kr.co.mashup.spacedeploy.spacedeploy.ETC.getStartDate
import kr.co.mashup.spacedeploy.spacedeploy.Error.DefaultErrorException
import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Header.HeaderDTO
import kr.co.mashup.spacedeploy.spacedeploy.login.LoginRepository
import kr.co.mashup.spacedeploy.spacedeploy.remind.RemindEntity
import kr.co.mashup.spacedeploy.spacedeploy.remind.RemindRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Service
class ArticleService(val articleRepository: ArticleRepository, val loginRepository: LoginRepository, val remindRepository: RemindRepository) {
    fun getArticle(year: Int, month: Int, day: Int, header: HeaderDTO): ArticleDto {
        val userEntity = loginRepository.findFirstByUid(header.uid!!)
        if (userEntity == null) {
            throw DefaultErrorException(ErrorsDetails(9000, "User not found"))
        }
        val userId = userEntity.userId!!
        val entity: ArticleEntity? = articleRepository.findFirstByUserIdAndYearAndMonthAndDay(userId, year, month, day)
        if (entity == null) {
            throw DefaultErrorException(ErrorsDetails(8000, "Article not found"))
        }
        return ArticleDto(entity!!.dailylogId!!, entity!!.emotion, entity!!.dailylogUpdateTime, entity!!.article)
    }

    fun postArticle(resDto: PostArticleDto, header: HeaderDTO) {
        if (resDto.emotion > 7) {
            throw DefaultErrorException(ErrorsDetails(7002, "Emotion value cannot exceed 7"))
        }
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(resDto.time.atZone(ZoneId.of("UTC")).toInstant())
        val year = calendar.get(Calendar.YEAR)
        val month = (calendar.get(Calendar.MONTH) + 1) % 13
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userEntity = loginRepository.findFirstByUid(header.uid!!)
        if (userEntity == null) {
            throw DefaultErrorException(ErrorsDetails(9000, "User not found"))
        }
        val userId = userEntity!!.userId!!

        val startDate = getStartDate(calendar)
        var remindEntity: RemindEntity? = remindRepository.findFirstByStartDate(startDate)
        if (remindEntity == null) {
            remindEntity = RemindEntity(startDate, getEndDate(calendar), userId)
            remindRepository.save(remindEntity)
        } else {
            remindEntity.isUpdated = true
            remindRepository.save(remindEntity)
        }
        val article = ArticleEntity(resDto.article, resDto.emotion, year, month, day, resDto.time, LocalDateTime.now(), LocalDateTime.now(), userId, remindEntity!!.remindId!!)
        articleRepository.save(article)
    }

    fun deleteArticle(dailylogId: Long, header: HeaderDTO) {
        val entity = articleRepository.findFirstByDailylogId(dailylogId = dailylogId)
        if (entity == null) {
            throw DefaultErrorException(ErrorsDetails(8000, "Article not found"))
        }
        articleRepository.delete(entity)
    }

    fun editArticle(articleDto: ArticleDto, header: HeaderDTO): ArticleDto {
        val result = articleRepository.findFirstByDailylogId(dailylogId = articleDto.dailylogId)
        if (result == null) {
            throw DefaultErrorException(ErrorsDetails(8000, "Article not found"))
        }
        result.emotion = articleDto.emotion
        result.article = articleDto.article
        result.dailylogDate = articleDto.time
        result.dailylogUpdateTime = LocalDateTime.now()
        val entity = articleRepository.save(result)
        val resDto = ArticleDto(entity.dailylogId!!, entity.emotion, entity.dailylogDate, entity.article)
        return resDto
    }
}