package kr.co.mashup.spacedeploy.spacedeploy.remind

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.Header.getHeader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "회고 API")
@RestController
@RequestMapping("/remind")
class RemindController(val remindService: RemindService) {
    @ApiOperation(value = "회고 목록 가져오기", notes = "회고 목록 가져오기")
    @GetMapping()
    fun getRemindList(request: HttpServletRequest): ResponseEntity<SpecificRemindListDto> {
        val header = getHeader(request)
        return ResponseEntity.status(HttpStatus.OK).body(remindService.getRemindLists(header))
    }

    @ApiOperation(value = "회고 자세히 보기", notes = "회고 자세히 보기")
    @GetMapping("/detail")
    fun getRemindDetail(defaultRemindDto: DefaultRemindDto,request: HttpServletRequest): ResponseEntity<RemindDto> {
        val header = getHeader(request)
        return ResponseEntity.status(HttpStatus.OK).body(remindService.getRemindDetail(defaultRemindDto, header))
    }

    @ApiOperation(value = "회고 작성", notes = "회고 작성")
    @PostMapping()
    fun writeRemind(postRemindDto: PostRemindDto, request: HttpServletRequest) {
        val header = getHeader(request)
        ResponseEntity.status(HttpStatus.OK).body(remindService.postRemind(postRemindDto, header))
    }

    @ApiOperation(value = "회고 수정", notes = "회고 수정")
    @PutMapping()
    fun editRemind(editRemindDto: PostRemindDto, request: HttpServletRequest) {
        val header = getHeader(request)
        ResponseEntity.status(HttpStatus.OK).body(remindService.editRemind(editRemindDto))
    }

    @ApiOperation(value = "회고 삭제", notes = "회고 삭제")
    @DeleteMapping()
    fun deleteRemind(deleteRemindDto: DefaultRemindDto, request: HttpServletRequest) {
        val header = getHeader(request)
        ResponseEntity.status(HttpStatus.OK).body(remindService.deleteRemind(deleteRemindDto))
    }
}