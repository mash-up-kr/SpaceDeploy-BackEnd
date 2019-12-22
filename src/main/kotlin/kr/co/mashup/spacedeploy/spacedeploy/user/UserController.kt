package kr.co.mashup.spacedeploy.spacedeploy.user

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "유저 API * 만드는중 *")
@RestController
@RequestMapping("/user")
class UserController(){
    @ApiOperation(value = "개인정보 가져오기", notes = "개인정보 가져오기")
    @GetMapping()
    fun getProfile(request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)

        ResponseEntity.status(HttpStatus.OK)
    }

    @ApiOperation(value = "개인정보 수정", notes = "개인정보 수정")
    @PutMapping()
    fun editProfile(request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)

        ResponseEntity.status(HttpStatus.OK)
    }

    @ApiOperation(value = "유저 탈퇴", notes = "유저 탈퇴")
    @DeleteMapping()
    fun deleteProfile(request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)

        ResponseEntity.status(HttpStatus.OK)
    }
}

@Api(description = "로그인 API * 만드는중 *")
@RestController
class LoginController() {
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    fun login(request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)

        ResponseEntity.status(HttpStatus.OK)
    }
}