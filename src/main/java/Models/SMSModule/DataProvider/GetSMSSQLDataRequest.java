package Models.SMSModule.DataProvider;


public class GetSMSSQLDataRequest {
    public  String PersonId;
    public  String TelNumber;
    public  String Consent;

    public String getPersonId() {
        return PersonId;
    }

    public String getTelNumber() {
        return TelNumber;
    }

    public String getConsent() {
        return Consent;
    }


    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public void setTelNumber(String telNumber) {
        TelNumber = telNumber;
    }

    public void setConsent(String consent) {
        Consent = consent;
    }


}
