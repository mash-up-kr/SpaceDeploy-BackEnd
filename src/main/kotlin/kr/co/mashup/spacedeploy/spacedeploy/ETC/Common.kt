package kr.co.mashup.spacedeploy.spacedeploy.ETC

import java.time.LocalDateTime
import java.util.*

fun getStartDate(calendar: Calendar): LocalDateTime {
    val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
    calendar.add(Calendar.DATE, -(weekDay - 1))
    val year = calendar.get(Calendar.YEAR)
    val month = (calendar.get(Calendar.MONTH) + 1) % 13
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return LocalDateTime.of(year,month,day,0,0,0,0)
}

fun getEndDate(calendar: Calendar): LocalDateTime {
    val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
    calendar.add(Calendar.DATE, (7 - weekDay))
    val year = calendar.get(Calendar.YEAR)
    val month = (calendar.get(Calendar.MONTH) + 1) % 13
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return LocalDateTime.of(year,month,day,0,0,0,0)
}