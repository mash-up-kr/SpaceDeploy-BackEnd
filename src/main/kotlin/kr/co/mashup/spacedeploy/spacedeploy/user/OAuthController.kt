package kr.co.mashup.spacedeploy.spacedeploy.user

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

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

fun getUserInfo(accessToken: String, provider: String) {
    when(Provider.fromString("provider")) {
        Provider.facebook -> getFacebookUserInfo(accessToken)
        Provider.instagram -> getInstagramUserInfo(accessToken)
        Provider.kakao -> getKakaoUserInfo(accessToken)
        Provider.none -> getUserInfoError()
    }
}

fun getKakaoUserInfo(accessToken: String): String? {
    val url: URL = URL("https://kapi.kakao.com/v2/user/me")
    try {
        val conn = url.openConnection() as HttpURLConnection
        conn.setRequestProperty("Authorization", "Bearer $accessToken")
        conn.setRequestProperty("accept", "application/json")

        if (conn.responseCode != HttpURLConnection.HTTP_OK) {
            throw RuntimeException("Request Failed. HTTP Error Code: " + conn.responseCode + conn.responseMessage)
        }

        val br = BufferedReader(InputStreamReader(conn.inputStream))
        val jsonString = StringBuffer()
        var line: String?
        while (br.readLine().also { line = it } != null) {
            jsonString.append(line)
        }

        br.close()
        conn.disconnect()
        return jsonString.toString()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

fun getInstagramUserInfo(accessToken: String): String? {
    val url: URL = URL("https://api.instagram.com/v1/users/self")
    try {
        val conn = url.openConnection() as HttpURLConnection
        conn.setRequestProperty("access_token", accessToken)

        if (conn.responseCode != HttpURLConnection.HTTP_OK) {
            throw RuntimeException("Request Failed. HTTP Error Code: " + conn.responseCode + conn.responseMessage)
        }

        val br = BufferedReader(InputStreamReader(conn.inputStream))
        val jsonString = StringBuffer()
        var line: String?
        while (br.readLine().also { line = it } != null) {
            jsonString.append(line)
        }

        br.close()
        conn.disconnect()
        return jsonString.toString()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

fun getFacebookUserInfo(accessToken: String): String? {
    val url: URL = URL("https://graph.facebook.com/debug_token")
    try {
        val conn = url.openConnection() as HttpURLConnection
        conn.setRequestProperty("input_token", accessToken)
        conn.setRequestProperty("access_token", "AppId")

        if (conn.responseCode != HttpURLConnection.HTTP_OK) {
            throw RuntimeException("Request Failed. HTTP Error Code: " + conn.responseCode + conn.responseMessage)
        }

        val br = BufferedReader(InputStreamReader(conn.inputStream))
        val jsonString = StringBuffer()
        var line: String?
        while (br.readLine().also { line = it } != null) {
            jsonString.append(line)
        }

        br.close()
        conn.disconnect()
        return jsonString.toString()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

fun getUserInfoError() {

}