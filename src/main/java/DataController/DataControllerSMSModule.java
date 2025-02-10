package DataController;

import Models.SMSModule.GetSMSRequest;
import SQLDatabase.SQLDatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataControllerSMSModule {

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

    public static List<GetSMSRequest> getSMSRequestLists (String query) throws SQLException {
        ResultSet resultSet;


        List<GetSMSRequest> getSMSRequestList = new ArrayList<>();
        Connection databaseSQL = SQLDatabaseAccess.getConnectionSMSModule();
        PreparedStatement preparedStatement = databaseSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            GetSMSRequest getSMSRequest = new GetSMSRequest();
            getSMSRequest.setPersonId(resultSet.getString("PersonId"));
            getSMSRequest.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequest.setConsent(resultSet.getString("Consent"));
            getSMSRequestList.add(getSMSRequest);
        }
        return getSMSRequestList;
    }



}
