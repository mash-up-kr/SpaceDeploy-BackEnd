package kr.co.mashup.spacedeploy.spacedeploy.Header

import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import javax.servlet.http.HttpServletRequest


fun getHeader(request: HttpServletRequest): HeaderDTO {
    val token = request.getHeader("Authorization")
    val provider = request.getHeader("Provider")
    val uid = getUID(token, provider)
    val timeZone = request.getHeader("TimeZone")
    val header = HeaderDTO(uid, timeZone, token)

    return header
}

data class HeaderDTO(
        val uid: String?,
        val timeZone: String,
        val token: String?
)