package kr.co.mashup.spacedeploy.spacedeploy.remind

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface RemindRepository : JpaRepository<RemindEntity, Int> {
    fun findAllByUserIdOrderByStartDate(userId: Long): Iterable<RemindEntity>?
    fun findFirstByRemindId(remindId: Long): RemindEntity?
    fun findFirstByStartDate(startDate: LocalDateTime): RemindEntity?
}