package DataProvider;

import DataController.GetDataControllerSMSModuleForDataProvider;
import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import org.testng.annotations.DataProvider;
import java.sql.SQLException;
import java.util.List;

import static DataController.GetDataControllerSMSModuleForDataProvider.getSMSRequestLists;

public class GetDataProviderClass {

    /** ბაზიდან ობიექტებად წამოღებული ინფორმაციის გადაცემა იმდენჯერ გადაცემა Call-თვის რამდენი ოებიექტიც დაბრუნდება**/
    @DataProvider (name = "getSMSRObjects")
    public static Object [][] getSMSRequestModel () throws SQLException {
         List<GetSMSDataRequest> getSMSRequestList = getSMSRequestLists(GetDataControllerSMSModuleForDataProvider.queryGetNumbers);
        Object[][] data = GetDataControllerSMSModuleForDataProvider.getSMSRObjects(getSMSRequestList);
        return data;
    }

    /** ბაზიდან თითოეული ელემენტად წამოღებული ინფორმაციის გადაცემა იმდენჯერ გადაცემა Call-თვის რამდენი ელემენტიც დაბრუნდება**/
    @DataProvider(name = "getSMSRObjectsIndividual")
    public static Object [][] getSMSRequestModelIndividual () throws SQLException {
         List<GetSMSDataRequest> getSMSRequestList = getSMSRequestLists(GetDataControllerSMSModuleForDataProvider.queryGetNumbers);
        Object[][] data = GetDataControllerSMSModuleForDataProvider.getSMSRObjectsIndividual(getSMSRequestList);
        return data;
    }


}
