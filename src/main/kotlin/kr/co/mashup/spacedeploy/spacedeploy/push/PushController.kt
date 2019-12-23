package kr.co.mashup.spacedeploy.spacedeploy.push

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(description = "Push")
@RestController
@RequestMapping("/push")
class PushController(val pushService : PushService){

//    @ApiOperation(value = "홈화면 정보 가져오기", notes = "지정한 연도의 감정기록과 각 달의 메인감정을 가져옵니다.")
    @GetMapping()
    fun postPush () =
            ResponseEntity.status(HttpStatus.OK).body(pushService.getToken())
}
