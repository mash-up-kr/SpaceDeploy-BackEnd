package kr.co.mashup.spacedeploy.spacedeploy.push

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import org.springframework.stereotype.Service
import java.io.FileInputStream


@Service
class PushService(){
    fun getToken() {
        getAccessToken()
    }

    fun getAccessToken(): String {
        val googleCredential = GoogleCredential
                .fromStream(FileInputStream("src/main/resources/service-account-file.json"))
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
        googleCredential.refreshToken
        print("abababababab")
        print(googleCredential)
        print(googleCredential.accessToken)
        return googleCredential.accessToken
    }
}
//    private static String getAccessToken() throws IOException {
//        GoogleCredential googleCredential = GoogleCredential
//                .fromStream(new FileInputStream("service-account.json"))
//                .createScoped(Arrays.asList(SCOPES));
//        googleCredential.refreshToken();
//        return googleCredential.getAccessToken();
//    }