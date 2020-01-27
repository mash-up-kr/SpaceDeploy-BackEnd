package kr.co.mashup.spacedeploy.spacedeploy.home

import kr.co.mashup.spacedeploy.spacedeploy.Error.DefaultErrorException
import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Header.HeaderDTO
import kr.co.mashup.spacedeploy.spacedeploy.article.ArticleEntity
import kr.co.mashup.spacedeploy.spacedeploy.article.ArticleRepository
import kr.co.mashup.spacedeploy.spacedeploy.login.LoginRepository
import kr.co.mashup.spacedeploy.spacedeploy.remind.DefaultRemindDto
import kr.co.mashup.spacedeploy.spacedeploy.remind.EmotionCount
import org.springframework.stereotype.Service
import java.time.Year


@Service
class HomeService(val homeRepository: ArticleRepository, val loginRepository: LoginRepository){
    fun getHome(header: HeaderDTO, year : Int): ResultRes {
        val userEntity = loginRepository.findFirstByUid(header.uid!!)
        if (userEntity == null) {
            throw DefaultErrorException(ErrorsDetails(9000, "User not found"))
        }
        val homeEntitys = homeRepository.findAllByUserIdAndYear(userEntity.userId!!, year)
        if (homeEntitys == null || homeEntitys.count() == 0) {
            throw DefaultErrorException(ErrorsDetails(8000, "Article not found"))
        }
        val yearRes : ArrayList<YearRes> = ArrayList()
        yearRes.add(YearRes(1,null, null))
        yearRes.add(YearRes(2,null, null))
        yearRes.add(YearRes(3,null, null))
        yearRes.add(YearRes(4,null, null))
        yearRes.add(YearRes(5,null, null))
        yearRes.add(YearRes(6,null, null))
        yearRes.add(YearRes(7,null, null))
        yearRes.add(YearRes(8,null, null))
        yearRes.add(YearRes(9,null, null))
        yearRes.add(YearRes(10,null, null))
        yearRes.add(YearRes(11,null, null))
        yearRes.add(YearRes(12,null, null))

        homeEntitys.forEach{
            if (yearRes[it.month - 1].emotionList == null) {
                val emotionRes : ArrayList<EmotionRes> = ArrayList()
                emotionRes.add(EmotionRes(it.emotion, it.day))
                yearRes[it.month - 1].emotionList = emotionRes
            } else {
                yearRes[it.month - 1].emotionList?.add(EmotionRes(it.emotion, it.day))
            }
        }

        yearRes.forEach {
            val mostEmotion = getMostEmotion(it)
            if (mostEmotion == -1) {

            } else {
                it.mostEmotion = mostEmotion
            }
        }

        return ResultRes(year = yearRes)
    }

    fun getMostEmotion(yearRes: YearRes): Int {
        val emotions = yearRes.emotionList

        var emotionCounts = MutableList<Int>(8) {0}
        if (emotions != null) {
            emotions.forEach {
                emotionCounts[it.emotion] += 1
            }
        }

        var bestIndex = -1
        var bestValue = 0
        emotionCounts.forEachIndexed { i, s ->
            if (bestValue < s) {
                bestIndex = i
                bestValue = s
            }
        }
        return bestIndex
    }
}