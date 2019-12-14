package kr.co.mashup.spacedeploy.spacedeploy

import kr.co.mashup.spacedeploy.spacedeploy.user.getUserInfo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpacedeployApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun tokenTest() {
        getUserInfo("5pg4NUK6zATK5LKmiKvH94P3dvynGAqTeSW6xAopdXYAAAFvBCdOZQ")
        assert(true)
    }
}
