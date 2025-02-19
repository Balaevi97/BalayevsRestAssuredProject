package DataProvider;

import DataController.PostDataControllerSMSModuleForDataProvider;
import Models.SMSModule.DataProvider.Post.PostSMSDataRequest;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.util.List;

import static DataController.PostDataControllerSMSModuleForDataProvider.postSMSDataRequestsList;


public class PostDataProviderClass {

    @DataProvider(name = "postSMSRObjects")
    public static Object [][] postSMSRequestModelObjects () throws SQLException {
        List<PostSMSDataRequest> postSMSDataRequests = postSMSDataRequestsList(PostDataControllerSMSModuleForDataProvider.queryPostNumbers);
        Object[][] data = PostDataControllerSMSModuleForDataProvider.postSMSRObjects(postSMSDataRequests);

        return data;
    }

    @DataProvider(name = "postSMSRObjectsIndividual")
    public static Object [][] postSMSRequestModelIndividual () throws SQLException {
        List<PostSMSDataRequest> postSMSDataRequestsList = postSMSDataRequestsList(PostDataControllerSMSModuleForDataProvider.queryPostNumbers);
        Object[][] data = PostDataControllerSMSModuleForDataProvider.postSMSRObjectsIndividual(postSMSDataRequestsList);

        return data;
    }



}
