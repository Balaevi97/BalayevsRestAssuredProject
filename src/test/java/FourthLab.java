import DataProvider.PostDataProviderClass;
import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import Models.SMSModule.DataProvider.Get.GetSMSResponse;
import Models.SMSModule.DataProvider.Post.PostSMSDataRequest;
import Steps.ConsentSteps;

import org.testng.annotations.Test;

public class FourthLab {

    ConsentSteps consentSteps = new ConsentSteps();
    GetSMSDataRequest getSMSDataRequest= new GetSMSDataRequest();
    @Test (dataProvider = "postSMSRObjects", dataProviderClass = PostDataProviderClass.class)
    public void postPersonId (PostSMSDataRequest postSMSDataRequest) {
        consentSteps.postConsent(postSMSDataRequest);

        if (postSMSDataRequest.getTelNumber() != null) {
            getSMSDataRequest.setTelNumber(postSMSDataRequest.getTelNumber());
        } else {
            getSMSDataRequest.setPersonId(postSMSDataRequest.getPersonId());
        }

        GetSMSResponse getSMSResponse = consentSteps.getConsent(getSMSDataRequest);

        System.out.println("მოსალოდნელი შედეგი: " + getSMSResponse.getData().getConsentStatusId());
        System.out.println("რეალური შედეგი: " + postSMSDataRequest.getStatus());
        consentSteps.compareConsent(getSMSResponse,
                                    postSMSDataRequest);
    }


}
