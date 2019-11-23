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
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun restApi(): Docket {
        // @formatter:off
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(
                                ResponseMessageBuilder()
                                        .code(500)
                                        .message("server error")
                                        .responseModel(
                                                ModelRef("Error")
                                        ).build()
                        )
                )
        // @formatter:on
    }

    private fun apiInfo(): ApiInfo {
        // @formatter:off
        return ApiInfoBuilder()
                .title("Spring boot Swagger")
                .description("api list")
                .contact(springfox.documentation.service.Contact("JS","no","jwjw.ow.com"))
                .version("1.0.0")
                .build()
        // @formatter:on
    }
}