package kr.co.mashup.spacedeploy.spacedeploy.oauth

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
            return none
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