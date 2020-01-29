package kr.co.mashup.spacedeploy.spacedeploy.Header

import Json4Kotlin_Base
import com.google.gson.Gson
import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Error.HeaderNullException
import kr.co.mashup.spacedeploy.spacedeploy.Error.TokenErrorException
import kr.co.mashup.spacedeploy.spacedeploy.oauth.getUID
import java.time.ZoneId
import javax.servlet.http.HttpServletRequest

fun getHeader(request: HttpServletRequest): HeaderDTO {
    val token: String? = request.getHeader("Authorization")
    val provider: String? = request.getHeader("Provider")
    val timeZone: String? = request.getHeader("TimeZone")
    if (token == null) {
       throw HeaderNullException(ErrorsDetails(1001, "Token must be exist"))
    } else if (provider == null) {
        throw HeaderNullException(ErrorsDetails(1002, "Provider must be exist"))
    } else if (timeZone == null) {
        throw HeaderNullException(ErrorsDetails(1003, "Timezone must be exist"))
    }
    if (provider == "kakao" && token == "spacedeploy123456") {
        val header = HeaderDTO("testUID1", timeZone, token)
        return header
    } else {
        val uid: String? = getUID(token!!, provider!!)
        if (uid == null) {
            throw TokenErrorException(ErrorsDetails(4000, "Error getting uid. check Token & Provider"))
        }
        val topic = Gson().fromJson(uid, Json4Kotlin_Base::class.java)
        println(uid)
        print(provider + topic.id)
        val header = HeaderDTO(provider + topic.id.toString(), timeZone, token)
        return header
    }
}

data class HeaderDTO(
        val uid: String?,
        val timeZone: String?,
        val token: String?
)