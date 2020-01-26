package kr.co.mashup.spacedeploy.spacedeploy.oauth

import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Error.UnknownProviderException

enum class Provider {
    facebook, instagram, kakao, none;

    companion object {
        fun fromString(provider: String): Provider {
            if (provider == "facebook") {
                return facebook
            } else if (provider == "instagram") {
                return instagram
            } else if (provider == "kakao") {
                return kakao
            }
            throw UnknownProviderException(ErrorsDetails(4001, "unknown Provider. Please select one (facebook, instagram, kakao)"))
        }
    }
}

fun getUID(accessToken: String, provider: String): String?  {
    when (Provider.fromString("provider")) {
        Provider.facebook -> return OAuthService().getFacebookUserInfo(accessToken)
        Provider.instagram -> return OAuthService().getInstagramUserInfo(accessToken)
        Provider.kakao -> return OAuthService().getKakaoUserInfo(accessToken)
        Provider.none -> return OAuthService().getUserInfoError()
    }
}