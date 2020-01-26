package kr.co.mashup.spacedeploy.spacedeploy.oauth

import kr.co.mashup.spacedeploy.spacedeploy.Error.ErrorsDetails
import kr.co.mashup.spacedeploy.spacedeploy.Error.TokenErrorException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OAuthService() {
    fun getKakaoUserInfo(accessToken: String): String? {
        val url: URL = URL("https://kapi.kakao.com/v2/user/me")
        try {
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "Bearer $accessToken")
            conn.setRequestProperty("accept", "application/json")

            if (conn.responseCode != HttpURLConnection.HTTP_OK) {
                throw TokenErrorException(ErrorsDetails(4000, "Error getting uid. check Token & Provider"))
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
                throw TokenErrorException(ErrorsDetails(4000, "Error getting uid. check Token & Provider"))
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
                throw TokenErrorException(ErrorsDetails(4000, "Error getting uid. check Token & Provider"))
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

    fun getUserInfoError(): String? {
        return null
    }
}