import DataController.DataControllerSMSModuleForDataProvider;
import Models.SMSModule.DataProvider.GetSMSSQLDataRequest;
import Models.SMSModule.ForCycle.GetSMSResponse;
import Models.SMSModule.ForCycle.SMSModuleApiResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Calls.GetConsent.GetConsentWithNumber;

public class ThirdLab {

    @Test (dataProvider = "getSMSRObjects", dataProviderClass = DataControllerSMSModuleForDataProvider.class)
    public void dataProviderTest (GetSMSSQLDataRequest getSMSSQLDataRequest) {

        GetSMSResponse getSMSResponse = GetConsentWithNumber(String.valueOf(getSMSSQLDataRequest.TelNumber));
        SMSModuleApiResponseData apiData = getSMSResponse.getData();
        Assert.assertEquals(String.valueOf(apiData.consentStatusId),
                                    getSMSSQLDataRequest.Consent,
                                    "Consent does not match"
            );
        }

    @Test (dataProvider = "getSMSRObjectsIndividual", dataProviderClass = DataControllerSMSModuleForDataProvider.class)
    public void dataProviderTestIndividual (String PersonN, String TelNumber, String Consent) {

        GetSMSResponse getSMSResponse = GetConsentWithNumber(TelNumber);
        SMSModuleApiResponseData apiData = getSMSResponse.getData();
        Assert.assertEquals(String.valueOf(apiData.consentStatusId),
                Consent,
                "Consent does not match"
        );

    }

}