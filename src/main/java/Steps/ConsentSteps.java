package Steps;

import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import Models.SMSModule.DataProvider.Get.GetSMSResponse;
import Models.SMSModule.DataProvider.Post.PostSMSDataRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import static Steps.ConsentCalls.GetConsentWithNumber;

public class ConsentSteps {

    @Step
    public GetSMSResponse getConsent (GetSMSDataRequest getSMSDataRequest) {
        GetSMSResponse getSMSResponse = new GetSMSResponse();
        Response response = GetConsentWithNumber(getSMSDataRequest);
        int statuscode = response.getStatusCode();

        if (statuscode == 200) {
            getSMSResponse = response.as(GetSMSResponse.class);
            Assert.assertEquals(statuscode, 200);
        } else {
            Assert.assertNotEquals(statuscode, 200);
        }
        return getSMSResponse;
    }

    @Step
    public void postConsent (PostSMSDataRequest postSMSDataRequest ) {

        Response response = ConsentCalls.PostConsentWithNumber(postSMSDataRequest);
        int statuscode = response.getStatusCode();

        if (statuscode == 200) {
            Assert.assertEquals(statuscode, 200);
        } else {
            Assert.assertNotEquals(statuscode, 200);
        }
    }

    public void compareConsent (GetSMSResponse getSMSResponse, PostSMSDataRequest postSMSDataRequest) {
       Assert.assertEquals(
               getSMSResponse.getData().getConsentStatusId(),
               postSMSDataRequest.getStatus());
       String s = "";

    }
}
