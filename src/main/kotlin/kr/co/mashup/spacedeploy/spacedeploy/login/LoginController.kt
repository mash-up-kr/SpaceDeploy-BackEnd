package kr.co.mashup.spacedeploy.spacedeploy.login

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@Api(description = "로그인 API")
@CrossOrigin
@RestController
class LoginController(val loginService: LoginService) {
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody pushToken: String, request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val uid = getUID(token, provider)
        val loginDto = LoginDto(uid, token, pushToken)
        ResponseEntity.status(HttpStatus.OK).body(loginService.login(loginDto))
    }
}