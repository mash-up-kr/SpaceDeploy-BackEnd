package kr.co.mashup.spacedeploy.spacedeploy.article

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kr.co.mashup.spacedeploy.spacedeploy.user.getUserInfo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "감정기록 API")
@RequestMapping("/dailyArticle")
@RestController
class ArticleController(val articleService: ArticleService) {

    @ApiOperation(value = "요일별 감정기록", notes = "지정된 요일의 감정기록을 가져옵니다.")
    @GetMapping()
    fun getArticle(@RequestParam year: Int, @RequestParam month: Int, @RequestParam day: Int, request: HttpServletRequest): ResponseEntity<ArticleDto> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticle(1, year, month, day))
    }

    @ApiOperation(value = "감정기록 저장", notes = "감정기록을 저장합니다.")
    @PostMapping()
    fun postArticle(@RequestBody articlePostDto: PostArticleDto, request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)
        ResponseEntity.status(HttpStatus.OK).body(articleService.postArticle(articlePostDto))
    }

    @ApiOperation(value = "감정기록 삭제", notes = "지정한 감정기록을 삭제합니다.")
    @DeleteMapping()
    fun deleteArticle(@PathVariable dailyLogId: Long, request: HttpServletRequest) {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)
        ResponseEntity.status(HttpStatus.OK).body(articleService.deleteArticle(dailyLogId))
    }

    @ApiOperation(value = "감정기록 수정", notes = "감정기록을 수정합니다.")
    @PutMapping()
    fun editArticle(@RequestBody articleDto: ArticleDto, request: HttpServletRequest): ResponseEntity<ArticleDto> {
        val token = request.getHeader("Authorization")
        val provider = request.getHeader("Provider")
        val userInfoJson = getUserInfo(token, provider)
        return ResponseEntity.status(HttpStatus.OK).body(articleService.editArticle(articleDto))
    }
}
