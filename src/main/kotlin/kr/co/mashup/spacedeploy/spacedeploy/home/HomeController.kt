package kr.co.mashup.spacedeploy.spacedeploy.home

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(val homeService : HomeService){

    @GetMapping("/home")
    fun getHomeList (@RequestParam year :Int): ResponseEntity<ResultRes> =
            ResponseEntity.status(HttpStatus.OK).body(homeService.getHome(year))



}