package kr.co.mashup.spacedeploy.spacedeploy.home


import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.MonthDay
import javax.persistence.*



@Entity
@Table(name = "DAILYLOG")
class Home (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var dailylogId: Long?,
            var userId: Long,
            var article: String,
            var emotion: Int,
            var dailylogCreateTime: LocalTime,
            var dailylogUpdateTime: LocalTime,
            var year: Int,
            var month: Int,
            var date: Int
)
{


    constructor(userId: Long,
                article: String,
                emotion: Int,
                dailylogCreateTime: LocalTime,
                dailylogUpdateTime: LocalTime,
                year: Int,
                month: Int,
                date: Int) :
            this(null,
                    userId,
                    article,
                    emotion,
                    dailylogCreateTime,
                    dailylogUpdateTime,
                    year,
                    month,
                    date)
}
