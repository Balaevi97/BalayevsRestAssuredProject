package Models.SMSModule.DataProvider.Get;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class GetSMSDataRequest {
    public  String PersonId;
    public  String TelNumber;
    public  String Consent;



}
