package kr.co.mashup.spacedeploy.spacedeploy.Error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.RuntimeException

@RestControllerAdvice
class ControllerAdviceRequestError: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UserException::class)])
    fun handleUnknownProvider(ex: UserException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(ex.error.code,
                ex.error.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(UnknownProviderException::class)])
    fun handleUnknownProvider(ex: UnknownProviderException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(ex.error.code,
                ex.error.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(TokenErrorException::class)])
    fun handleTokenError(ex: TokenErrorException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(ex.error.code,
                ex.error.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(HeaderNullException::class)])
    fun handleHeaderisNil(ex: HeaderNullException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(ex.error.code,
                ex.error.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}
class UserException(val error: ErrorsDetails): RuntimeException()
class UnknownProviderException(val error: ErrorsDetails): RuntimeException()
class TokenErrorException(val error: ErrorsDetails): RuntimeException()
class HeaderNullException(val error: ErrorsDetails): RuntimeException()
data class ErrorsDetails(val code: Int, val message: String)