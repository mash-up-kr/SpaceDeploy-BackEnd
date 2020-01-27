package kr.co.mashup.spacedeploy.spacedeploy.remind

import kr.co.mashup.spacedeploy.spacedeploy.Error.DefaultErrorException
import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Header.HeaderDTO
import kr.co.mashup.spacedeploy.spacedeploy.article.ArticleRepository
import kr.co.mashup.spacedeploy.spacedeploy.login.LoginRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RemindService(val remindRepository: RemindRepository, val loginRepository: LoginRepository, val articleRepository: ArticleRepository) {
    fun getRemindLists(header: HeaderDTO): SpecificRemindListDto {

        val userEntity = loginRepository.findFirstByUid(header.uid!!)
        if (userEntity == null) {
            throw DefaultErrorException(ErrorsDetails(9000, "User not found"))
        }
        val remindEntity = remindRepository.findAllByUserIdOrderByStartDate(userEntity!!.userId!!)
        val remindMap = remindEntity!!.map {
            RemindListDto(it.remindId!!,it.bestEmotion,it.startDate,it.endDate,it.title,it.command,it.isUpdated)
        }

        if (remindMap.count() == 0) {
            throw DefaultErrorException(ErrorsDetails(7000,"No Content"))
        }
        return SpecificRemindListDto(remindMap)
    }

    fun getRemindDetail(defaultRemindDto: DefaultRemindDto, header: HeaderDTO): RemindDto {
        val remindEntity = remindRepository.findFirstByRemindId(defaultRemindDto.remindId)
        if (remindEntity == null) {
            throw DefaultErrorException(ErrorsDetails(7001,"Remind not found"))
        }

        return RemindDto(remindEntity.remindId!!,getEmotionRank(defaultRemindDto)!!,remindEntity.startDate,remindEntity.endDate,remindEntity.title,remindEntity.command)
    }

    fun postRemind(postRemindDto: PostRemindDto, header: HeaderDTO) {
        val remindEntity = remindRepository.findFirstByRemindId(postRemindDto.remindId)
        if (remindEntity == null) {
            throw DefaultErrorException(ErrorsDetails(7001,"Remind not found"))
        }
        remindEntity.command = postRemindDto.command
        remindEntity.title = postRemindDto.title
        remindEntity.remindUpdateTime = LocalDateTime.now()
        remindEntity.bestEmotion = getBestEmotion(DefaultRemindDto(postRemindDto.remindId))
        remindEntity.isUpdated = false

        remindRepository.save(remindEntity)
    }

    fun editRemind(postRemindDto: PostRemindDto) {
        val remindEntity = remindRepository.findFirstByRemindId(postRemindDto.remindId)
        if (remindEntity == null) {
            throw DefaultErrorException(ErrorsDetails(7001,"Remind not found"))
        }
        remindEntity.command = postRemindDto.command
        remindEntity.title = postRemindDto.title
        remindEntity.remindUpdateTime = LocalDateTime.now()
        remindEntity.bestEmotion = getBestEmotion(DefaultRemindDto(postRemindDto.remindId))
        remindEntity.isUpdated = false

        remindRepository.save(remindEntity)
    }

    fun deleteRemind(deleteRemindDto: DefaultRemindDto) {
        val remindEntity = remindRepository.findFirstByRemindId(deleteRemindDto.remindId)
        if (remindEntity == null) {
            throw DefaultErrorException(ErrorsDetails(7001,"Remind not found"))
        }

        remindRepository.delete(remindEntity!!)
    }

    fun getBestEmotion(defaultRemindDto: DefaultRemindDto): Int {
        val counts = getEmotionRank(defaultRemindDto)
        return counts?.first()!!.emotion
    }

    fun getEmotionRank(defaultRemindDto: DefaultRemindDto): List<EmotionCount?> {
        val articleMap = articleRepository.findAllByRemindId(defaultRemindDto.remindId)
        if (articleMap == null || articleMap.count() == 0) {
            throw DefaultErrorException(ErrorsDetails(8000, "Article not found"))
        }
        var counts = MutableList<Int>(8) {0}
        articleMap!!.forEach {
            counts[it.emotion] += 1
        }


        var emotionCounts = ArrayList<EmotionCount?>()
        counts.forEachIndexed{ i, s ->
            emotionCounts.add(EmotionCount(i,s))
        }

        var sortedList = emotionCounts.sortedWith(compareBy({ it!!.count })).reversed()
        return sortedList
    }
}