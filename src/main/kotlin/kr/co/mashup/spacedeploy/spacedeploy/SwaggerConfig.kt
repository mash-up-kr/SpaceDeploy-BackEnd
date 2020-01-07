package kr.co.mashup.spacedeploy.spacedeploy

import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.schema.ModelRef
import springfox.documentation.builders.ResponseMessageBuilder
import org.springframework.web.bind.annotation.RequestMethod
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Contact
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
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
                .host("spacedeploy.pw")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
                .build()
                .useDefaultResponseMessages(false)
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(provider(), token()))
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
- Authorization: token
- Provider: facebook | kakao |instgram

                    """)
                .version("1.0.0")
                .build()
        // @formatter:on
    }

    private fun securityContext(): SecurityContext =
            SecurityContext.builder()
                    .securityReferences(listOf(defaultAuth()))
                    .forPaths(PathSelectors.regex("/*"))
                    .build()

    private fun defaultAuth(): SecurityReference =
            SecurityReference("Authorization", arrayOf(AuthorizationScope("global", "accessEverything")))

    private fun token(): ApiKey = ApiKey("Token", "Authorization", "header")
    private fun provider(): ApiKey = ApiKey("Provider", "provider", "header")
}