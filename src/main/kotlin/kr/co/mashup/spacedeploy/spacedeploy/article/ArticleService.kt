package kr.co.mashup.spacedeploy.spacedeploy.article

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getArticle(user_id: Int, year: Int, month: Int, day: Int): ResArticleDto {
        val result = articleRepository.findFirstByUserIdAndYearAndMonthAndDay(user_id, year, month, day)
        return ResArticleDto(result.dailylogId!!, result.emotion, result.dailylogUpdateTime, result.article)
    }

    fun postArticle(resDto: ResPostArticleDto) {
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(resDto.time.atZone(ZoneId.of("Asia/Seoul")).toInstant())
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val article = ArticleEntity(resDto.article, resDto.emotion, year, month, day, resDto.time, resDto.time, resDto.userId)
        articleRepository.save(article)
    }
}