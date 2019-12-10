package kr.co.mashup.spacedeploy.spacedeploy.error

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ErrorProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.context.request.ServletWebRequest
import java.util.*

@RestController
class DefaultErrorController @Autowired constructor(var serverProperties: ServerProperties, var errorAttributes: ErrorAttributes, var messageSource: MessageSource) : AbstractErrorController(errorAttributes) {

    companion object {
        const val ERROR_PATH = "\${error.path:/error}"
        const val ERROR_DEFAULT_MESSAGE = "No message available."
    }

    @RequestMapping(ERROR_PATH)
    fun error(servletWebRequest: ServletWebRequest, locale: Locale): ResponseEntity<Map<String, Any>> {
        val body = errorAttributes.getErrorAttributes(servletWebRequest, isIncludeStackTrace(servletWebRequest))
        val status = when(body["status"]) {
            is Int -> HttpStatus.valueOf(body["status"] as Int)
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        body["message"] = messageSource.getMessage("error.$status", null, ERROR_DEFAULT_MESSAGE, locale)

        return ResponseEntity.status(status).body(body)
    }

    fun isIncludeStackTrace(requestAttributes: ServletRequestAttributes) =
            when(serverProperties.error.includeStacktrace) {
                ErrorProperties.IncludeStacktrace.ALWAYS -> true
                ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM -> requestAttributes.request.getParameter("trace")?.toLowerCase()?.equals("true") ?: false
                else -> false
            }

    override fun getErrorPath() = serverProperties.error.path
}

