package Models.SMSModule;

public class GetSMSResponse {
    public SMSModuleApiResponseData data;
    public GetSMSResponse smsResponse;

    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;
    public Object validationErrors;

    public SMSModuleApiResponseData getData() {
        return data;
    }
    public GetSMSResponse getSMSResponseData (){
        return smsResponse;
    }

    public Object getMessage() {
        return message;
    }

    public Object getDetailsMessage() {
        return detailsMessage;
    }

    public int getExternalState() {
        return externalState;
    }

    public int getState() {
        return state;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public Object getValidationErrors() {
        return validationErrors;
    }

}
