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
                .securitySchemes(listOf(providerAuthScheme(), tokenAuthScheme()))
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
```

### Time Formmat 

    yyyy-MM-dd'T'HH:mm:ss.SSS

                    """)
                .version("1.0.0")
                .build()
        // @formatter:on
    }


    private fun actuatorSecurityContext(): SecurityContext? {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(providerAuthReference(), tokenAuthReference()))
                .forPaths(PathSelectors.ant("/**"))
                .build()
    }

    private fun providerAuthScheme(): SecurityScheme? {
        return ApiKey("Provider", "Provider", "header")
    }

    private fun tokenAuthScheme(): SecurityScheme? {
        return ApiKey("Authorization", "Authorization", "header")
    }

    private fun providerAuthReference(): SecurityReference? {
        return SecurityReference("Provider", arrayOfNulls(0))
    }

    private fun tokenAuthReference(): SecurityReference? {
        return SecurityReference("Authorization", arrayOfNulls(0))
    }
}