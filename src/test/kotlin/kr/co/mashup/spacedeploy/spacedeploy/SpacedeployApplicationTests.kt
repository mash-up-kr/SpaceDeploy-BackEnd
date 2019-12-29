package kr.co.mashup.spacedeploy.spacedeploy

import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpacedeployApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun kakaoTokenTest() {
        getUID("5pg4NUK6zATK5LKmiKvH94P3dvynGAqTeSW6xAopdXYAAAFvBCdOZQ", "kakao")
        assert(true)
    }

    fun facebookTokenTest() {
        getUID("EAAMYTVEn0OABAOxZBDvKtcq19bFsZB4GiQ5dJZCnfKbdGP6Ob4xMVdpZBOa4iH0Jjk8g79hodqGcpv8ycZB08ZBKSJti0ctt5nTOz58fRJjESZBZB8IRIp6mniwz0V03Uh4Ngve94zirs6XExboUVmsBOhzIwDcsCNFZA6YZBTUHdgwgZDZD", "facebook")
    }

}
