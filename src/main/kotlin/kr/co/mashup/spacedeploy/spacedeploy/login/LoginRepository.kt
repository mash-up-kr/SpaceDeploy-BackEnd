package kr.co.mashup.spacedeploy.spacedeploy.login

import kr.co.mashup.spacedeploy.spacedeploy.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepository : JpaRepository<UserEntity, Int> {
    fun findFirstByUid(uid: String): UserEntity
}