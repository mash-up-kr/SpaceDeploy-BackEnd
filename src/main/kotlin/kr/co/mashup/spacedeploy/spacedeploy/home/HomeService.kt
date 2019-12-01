package kr.co.mashup.spacedeploy.spacedeploy.home

import org.springframework.stereotype.Service


@Service
class HomeService(val HomeRepository: HomeRepository){

    fun getHome( month : Int): ResultRes {

        val emotionRes : ArrayList<EmotionRes> = ArrayList()
        emotionRes.add(EmotionRes(emotion = 2, day = 25))
        val yearRes : ArrayList<YearRes> = ArrayList()
        yearRes.add(YearRes(10,2, emotionRes))
        yearRes.add(YearRes(11,1, emotionRes))

        return ResultRes(remind = true, year = yearRes)
    }
}