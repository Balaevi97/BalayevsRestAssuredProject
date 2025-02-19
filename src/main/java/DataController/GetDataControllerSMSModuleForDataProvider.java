package DataController;

import Models.SMSModule.DataProvider.Get.GetSMSDataRequest;
import SQLDatabase.SQLDatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataControllerSMSModuleForDataProvider {

    public static String queryGetNumbers = """
            USE SMSModuleDB
            
              IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
                DROP TABLE #tmp
            
                SELECT s.lenTelNumber
            		,MAX(s.PersonId) AS PersonId
            		INTO #tmp
            		FROM (SELECT DISTINCT
            					LEN(s.TelNumber) AS lenTelNumber
            					,s.PersonId
            		FROM SMSModuleDB.dbo.AdSMSConsent s
            		WHERE s.PersonId IS NOT NULL) AS s
            		GROUP BY s.lenTelNumber
            
                SELECT t.*
            			,c.TelNumber
            			,c.Consent
            
            		FROM #tmp t
            		INNER JOIN dbo.AdSMSConsent c ON c.PersonId = t.PersonId AND t.lenTelNumber = LEN(c.TelNumber)
            		ORDER BY t.lenTelNumber ASC
            
            """;



    /** ინფორმაციის წამოღება ბაზიდან **/

    public static List<GetSMSDataRequest> getSMSRequestLists (String query) throws SQLException {
        ResultSet resultSet;

        List<GetSMSDataRequest> getSMSRequestList = new ArrayList<>();
        Connection databaseSQL = SQLDatabaseAccess.getConnectionSMSModule();
        PreparedStatement preparedStatement = databaseSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            GetSMSDataRequest getSMSRequest = new GetSMSDataRequest();
            getSMSRequest.setPersonId(resultSet.getString("PersonId"));
            getSMSRequest.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequest.setConsent(resultSet.getString("Consent"));
            getSMSRequestList.add(getSMSRequest);
        }
        return getSMSRequestList;
    }

/** ბაზიდან წამოღებული ინფორმაციის შენახვა ობიექტებად **/

    public static Object [][] getSMSRObjects (List<GetSMSDataRequest> getSMSRequestList) {
        Object[][] data = new Object[getSMSRequestList.size()][1];
        for (int i = 0; i <getSMSRequestList.size() ; i++) {
            data[i][0] = getSMSRequestList.get(i);
        }
        return data;
    }

    /** ბაზიდან წამოღებული ინფორმაციის შენახვა თითოეული ელემენტად **/
    public static Object [][] getSMSRObjectsIndividual (List<GetSMSDataRequest> getSMSRequestList) {

        Object[][] data = new Object[getSMSRequestList.size()][3];
        for (int i = 0; i <getSMSRequestList.size() ; i++) {
            data[i][0] = getSMSRequestList.get(i).getPersonId();
            data[i][1] = getSMSRequestList.get(i).getTelNumber();
            data[i][2] = getSMSRequestList.get(i).getConsent();
        }
        return data;
    }


}
