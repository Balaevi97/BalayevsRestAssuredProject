package DataController;

import Models.SMSModule.DataProvider.GetSMSSQLDataRequest;
import SQLDatabase.SQLDatabaseAccess;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataControllerSMSModuleForDataProvider {

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

    public static List<GetSMSSQLDataRequest> getSMSRequestLists (String query) throws SQLException {
        ResultSet resultSet;


        List<GetSMSSQLDataRequest> getSMSRequestList = new ArrayList<>();
        Connection databaseSQL = SQLDatabaseAccess.getConnectionSMSModule();
        PreparedStatement preparedStatement = databaseSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            GetSMSSQLDataRequest getSMSRequest = new GetSMSSQLDataRequest();
            getSMSRequest.setPersonId(resultSet.getString("PersonId"));
            getSMSRequest.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequest.setConsent(resultSet.getString("Consent"));
            getSMSRequestList.add(getSMSRequest);
        }
        return getSMSRequestList;
    }


    @DataProvider(name = "getSMSRObjects")
    public static Object [][] getSMSRObjects () throws SQLException {
        List<GetSMSSQLDataRequest> getSMSRequestList = getSMSRequestLists(DataControllerSMSModuleForDataProvider.queryGetNumbers);
        Object[][] data = new Object[getSMSRequestList.size()][1];
        for (int i = 0; i <getSMSRequestList.size() ; i++) {
            data[i][0] = getSMSRequestList.get(i);
        }
        return data;
    }

    @DataProvider(name = "getSMSRObjectsIndividual")
    public static Object [][] getSMSRObjectsIndividual () throws SQLException {
        List<GetSMSSQLDataRequest> getSMSRequestList = getSMSRequestLists(DataControllerSMSModuleForDataProvider.queryGetNumbers);
        Object[][] data = new Object[getSMSRequestList.size()][3];
        for (int i = 0; i <getSMSRequestList.size() ; i++) {
            data[i][0] = getSMSRequestList.get(i).getPersonId();
            data[i][1] = getSMSRequestList.get(i).getTelNumber();
            data[i][2] = getSMSRequestList.get(i).getConsent();
        }
        return data;
    }


}
