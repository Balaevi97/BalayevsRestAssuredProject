import DataProvider.GetDataProviderClass;
import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import Models.SMSModule.DataProvider.Get.GetSMSResponse;
import Models.SMSModule.DataProvider.Get.SMSModuleApiResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Steps.ConsentCalls.GetConsentWithNumber;

public class ThirdLab {

    @Test (dataProvider = "getSMSRObjects", dataProviderClass = GetDataProviderClass.class)
    public void dataProviderTest (GetSMSDataRequest getSMSSQLDataRequest) {
        GetSMSResponse getSMSResponse = GetConsentWithNumber(getSMSSQLDataRequest).as(GetSMSResponse.class);

        SMSModuleApiResponseData apiData = getSMSResponse.getData();
        Assert.assertEquals(String.valueOf(apiData.consentStatusId),
                                    getSMSSQLDataRequest.Consent,
                                    "Consent does not match"
            );
        }

    @Test (dataProvider = "getSMSRObjectsIndividual", dataProviderClass = GetDataProviderClass.class)
    public void dataProviderTestIndividual (GetSMSDataRequest PersonN, GetSMSDataRequest TelNumber, GetSMSDataRequest Consent) {

        GetSMSResponse getSMSResponse = GetConsentWithNumber(TelNumber).as(GetSMSResponse.class);;
        SMSModuleApiResponseData apiData = getSMSResponse.getData();

        Assert.assertEquals(String.valueOf(apiData.getConsentStatusId()),
                String.valueOf(Consent),
                "Consent does not match"
        );

    }

}