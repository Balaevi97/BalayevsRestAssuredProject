package Models.SMSModule.DataProvider.Post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostSMSDataRequest {

    public String personId;
    public String telNumber;
    public String status;
    public String channelId;

}