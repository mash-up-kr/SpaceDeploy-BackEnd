package kr.co.mashup.spacedeploy.spacedeploy.login

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.Header.getHeader
import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "로그인 API")
@RestController
class LoginController(val loginService: LoginService) {
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody pushToken: String, request: HttpServletRequest) {
        val header = getHeader(request)
        val loginDto = LoginDto(header.uid, header.token, pushToken)
        ResponseEntity.status(HttpStatus.OK).body(loginService.login(loginDto))
    }
}