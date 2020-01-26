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

    @ApiOperation(value = "푸쉬발송", notes = "내용의 푸쉬를 발송합니다")
    @GetMapping()
    fun postPush (): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(pushService.getToken())
    }
}
