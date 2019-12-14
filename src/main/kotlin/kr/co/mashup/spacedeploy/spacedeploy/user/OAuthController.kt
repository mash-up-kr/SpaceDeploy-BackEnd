package kr.co.mashup.spacedeploy.spacedeploy.user

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun getUserInfo(accessToken: String): String? {
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