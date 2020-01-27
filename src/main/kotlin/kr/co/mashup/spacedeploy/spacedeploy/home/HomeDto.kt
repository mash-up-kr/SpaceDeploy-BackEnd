package kr.co.mashup.spacedeploy.spacedeploy.home

data class HomeReq (val year: Int)

data class HomeRes(val result: ResultRes)

data class ResultRes(val year: ArrayList<YearRes>)

data class YearRes(val month: Int, var mostEmotion: Int?, var emotionList: ArrayList<EmotionRes>?)

data class EmotionRes (var emotion: Int,var day: Int)
