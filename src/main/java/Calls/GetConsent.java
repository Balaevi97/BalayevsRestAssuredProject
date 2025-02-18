package Calls;

import Models.SMSModule.ForCycle.GetSMSResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetConsent {

    public static GetSMSResponse  GetConsentWithNumber (String telNum) {
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNum);

        return response.as(GetSMSResponse .class);


    }
}
