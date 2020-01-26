package kr.co.mashup.spacedeploy.spacedeploy

import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun restApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
//                .host("spacedeploy.pw")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
                .build()
                .useDefaultResponseMessages(false)
                .securityContexts(listOf(actuatorSecurityContext()))
                .securitySchemes(listOf(timeZoneAuthScheme(), providerAuthScheme(), tokenAuthScheme()))
    }

    private fun apiInfo(): ApiInfo {
        // @formatter:off
        return ApiInfoBuilder()
                .title("SpaceDeploy API명세")
                .description("""
## 우주배포 팀 화이팅
### 주소
www.spacedeploy.pw
### Header

```
Authorization: token
Provider: facebook | kakao |instgram
TimeZone: ex) Asia/Seoul
```

### Time Formmat 

    yyyy-MM-dd'T'HH:mm:ss.SSS

### Emotion Enum
각 기분에 해당되는 Int값을 사용합니다.
- 기쁨: pleasure - 0
- 행복: Happiness - 1
- 평온: tranquility - 2
- 만족: Satisfaction - 3
- 화남: aggro - 4
- 우울: depressed - 5
- 피곤: tired - 6
- 슬픔 : sadness - 7

### Error code
- 1001: 헤더에 토큰이 없음
- 1002: 헤더에 프로바이더가 없음
- 1003: 헤더에 타임존이 없음

- 4000: UID를 가져오는데 문제 생김 (Token, Provider 확인)
- 4001: 알수없는 Provider 위에 입력된 프로바이더중 입력
                    """)
                .version("1.0.0")
                .build()
        // @formatter:on
    }


    private fun actuatorSecurityContext(): SecurityContext? {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(timeZoneAuthReference(), providerAuthReference(), tokenAuthReference()))
                .forPaths(PathSelectors.ant("/**"))
                .build()
    }

    private fun providerAuthScheme(): SecurityScheme? {
        return ApiKey("Provider", "Provider", "header")
    }

    private fun tokenAuthScheme(): SecurityScheme? {
        return ApiKey("Authorization", "Authorization", "header")
    }

    private fun timeZoneAuthScheme(): SecurityScheme? {
        return ApiKey("TimeZone", "TimeZone", "header")
    }

    private fun providerAuthReference(): SecurityReference? {
        return SecurityReference("Provider", arrayOfNulls(0))
    }

    private fun tokenAuthReference(): SecurityReference? {
        return SecurityReference("Authorization", arrayOfNulls(0))
    }

    private fun timeZoneAuthReference(): SecurityReference? {
        return SecurityReference("TimeZone", arrayOfNulls(0))
    }
}