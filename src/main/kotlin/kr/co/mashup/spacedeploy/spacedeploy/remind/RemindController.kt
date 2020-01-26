package kr.co.mashup.spacedeploy.spacedeploy.remind

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@Api(description = "회고 API")
@RestController
@RequestMapping("/remind")
class RemindController() {
    @ApiOperation(value = "회고 목록 가져오기", notes = "회고 목록 가져오기")
    @GetMapping()
    fun getRemindList(request: HttpServletRequest): ResponseEntity<SpecificRemindListDto> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUID(token, provider)
        return ResponseEntity.status(HttpStatus.OK).body(SpecificRemindListDto(LocalDateTime.now(), null))
    }

    @ApiOperation(value = "회고 작성", notes = "회고 작성")
    @PostMapping()
    fun writeRemind(postRemindDto: PostRemindDto, request: HttpServletRequest): ResponseEntity<RemindDto> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUID(token, provider)
        return ResponseEntity.status(HttpStatus.OK).body(RemindDto(0, null, LocalDateTime.now(), LocalDateTime.now(), null, null))
    }

    @ApiOperation(value = "회고 수정", notes = "회고 수정")
    @PutMapping()
    fun editRemind(editRemindDto: EditRemindDto, request: HttpServletRequest): ResponseEntity<RemindDto> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUID(token, provider)
        return ResponseEntity.status(HttpStatus.OK).body(RemindDto(0, null, LocalDateTime.now(), LocalDateTime.now(), null, null))
    }

    @ApiOperation(value = "회고 삭제", notes = "회고 삭제")
    @DeleteMapping()
    fun deleteRemind(editRemindDto: EditRemindDto, request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUID(token, provider)
        ResponseEntity.status(HttpStatus.OK)
    }
}