package kr.co.mashup.spacedeploy.spacedeploy.home

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "홈화면 API")
@RestController
@RequestMapping("/home")
class HomeController(val homeService : HomeService){

    @ApiOperation(value = "홈화면 정보 가져오기", notes = "지정한 연도의 감정기록과 각 달의 메인감정을 가져옵니다.")
    @GetMapping()
    fun getHomeList (@RequestParam year :Int, request: HttpServletRequest): ResponseEntity<ResultRes> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUID(token, provider)

        return ResponseEntity.status(HttpStatus.OK).body(homeService.getHome(year))
    }
}