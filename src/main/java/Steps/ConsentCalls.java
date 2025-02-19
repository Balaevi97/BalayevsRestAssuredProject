package Steps;

import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import Models.SMSModule.DataProvider.Post.PostSMSDataRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ConsentCalls {

    public static Response  GetConsentWithNumber (GetSMSDataRequest getSMSDataRequest) {
        String URL;

        if (getSMSDataRequest.getTelNumber() != null) {
            URL =  "http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSDataRequest.getTelNumber() ;
        } else {
            URL =  "http://10.195.105.66:7000/api/Consent?PersonId=" +  getSMSDataRequest.getPersonId() ;

        }
            return given()
                    .header("Content-type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .get(URL);
    }


    public static Response  PostConsentWithNumber (PostSMSDataRequest postSMSDataRequest) {
        return   given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(postSMSDataRequest)
                .post("http://10.195.105.66:7000/api/Consent" );
    }
}
