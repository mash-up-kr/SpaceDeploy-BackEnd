package kr.co.mashup.spacedeploy.spacedeploy.remind

import org.springframework.data.jpa.repository.JpaRepository

interface RemindRepository : JpaRepository<RemindEntity, Int> {
}