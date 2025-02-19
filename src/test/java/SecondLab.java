import DataController.DataControllerSMSModule;
import Models.SMSModule.ForCycle.GetSMSResponse;
import Models.SMSModule.ForCycle.GetSMSSQLDataRequest;
import Models.SMSModule.ForCycle.SMSModuleApiResponseData;
import Models.Users.CreateUsersResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.sql.SQLException;
import java.util.List;
import static DataController.DataControllerSMSModule.getSMSRequestLists;
import static io.restassured.RestAssured.given;

public class SecondLab {
    @Test
    public void SMSModuleDB () throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        List<GetSMSSQLDataRequest> getSMSRequests = getSMSRequestLists(DataControllerSMSModule.queryGetNumbers);
        for (int i= 0; i< getSMSRequests.size(); i++) {
            Response response = given()
                    .header("Content-type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequests.get(i).getTelNumber());
            GetSMSResponse getSMSResponse = response.as(GetSMSResponse.class);
            SMSModuleApiResponseData apiData = getSMSResponse.getData();
            softAssert.assertEquals(
                    String.valueOf(apiData.setConsentStatusId()),
                    String.valueOf(getSMSRequests.get(i).getConsent()),
                    "Consent does not match"
                    );
        }
        softAssert.assertAll();
    }
    @Test
    public void  newLab () {
        SoftAssert softAssert = new SoftAssert();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Bahruz");
        jsonObject.put("job", "Quality Assurance");
        Response response = given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/users");
        CreateUsersResponseModel createUsersResponseModel = response.as(CreateUsersResponseModel.class);
        System.out.println(createUsersResponseModel.getName());
        System.out.println(createUsersResponseModel.getJob());
        System.out.println(createUsersResponseModel.getId());
        System.out.println(createUsersResponseModel.getCreatedAt());
        softAssert.assertEquals(createUsersResponseModel.getName(), "Bahruz", "Name does not match!");
        softAssert.assertEquals(createUsersResponseModel.getJob(), "Quality Assurance","Job does not match!");
        softAssert.assertEquals(createUsersResponseModel.getName(), "Bahruz", "Name does not match!");
        softAssert.assertAll();
    }
}