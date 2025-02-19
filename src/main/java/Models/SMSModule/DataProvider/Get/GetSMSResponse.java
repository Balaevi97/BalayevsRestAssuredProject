package Models.SMSModule.DataProvider.Get;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSMSResponse {
    public SMSModuleApiResponseData data;
    public GetSMSResponse smsResponse;

    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;
    public Object validationErrors;


}
