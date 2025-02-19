package DataController;

import Models.SMSModule.DataProvider.Post.PostSMSDataRequest;
import SQLDatabase.SQLDatabaseAccess;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDataControllerSMSModuleForDataProvider {

    public static String queryPostNumbers = """
            USE SMSModuleDB
            
                        IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
                            DROP TABLE #tmp
            
                        SELECT * , ROW_NUMBER() OVER (ORDER BY a.PersonId ASC) AS RowNum
                        INTO #tmp
                        FROM
                        (SELECT TOP 3 con.PersonId
                        			,cast(con.TelNumber AS VARCHAR) AS TelNumber
                        			,con.Consent
                        			,con.Channel

                        			FROM SMSModuleDB.dbo.AdSMSConsent (NOLOCK) con
                        			WHERE con.PersonId IS NOT NULL
                        				AND con.TelNumber IS NOT NULL
                        				AND con.Channel IS NOT NULL

                        UNION

                        SELECT TOP 1	c.PersonId
                        				,c.Contact
                        				,'3' AS Consent
                        				,'121314' AS Channel

                        FROM  CredoBnk.person.Contact (NOLOCK) c
                        LEFT JOIN SMSModuleDB.dbo.AdSMSConsent (NOLOCK) con on con.PersonId = c.PersonId
                        LEFT JOIN SMSModuleDB.dbo.AdSMSConsent (NOLOCK) cont on c.Contact  = cont.TelNumber

                        WHERE	con.PersonId IS NULL
                        		AND cont.PersonId IS NULL

                        ) AS A

                        UPDATE #tmp SET PersonId = null where  RowNum = 1
                        UPDATE #tmp SET TelNumber = null where  RowNum = 2
                        UPDATE #tmp SET Channel = null where  RowNum = 3

                        SELECT * FROM #tmp

            """;

    /** ინფორმაციის წამოღება ბაზიდან **/

    public static List<PostSMSDataRequest> postSMSDataRequestsList (String query) throws SQLException {
        ResultSet resultSet;

        List<PostSMSDataRequest> postSMSDataRequests = new ArrayList<>();
        Connection databaseSQL = SQLDatabaseAccess.getConnectionSMSModule();
        PreparedStatement preparedStatement = databaseSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PostSMSDataRequest postSMSDataRequest = new PostSMSDataRequest();
            postSMSDataRequest.setPersonId(resultSet.getString("PersonId"));
            postSMSDataRequest.setTelNumber(resultSet.getString("TelNumber"));
            postSMSDataRequest.setStatus(resultSet.getString("Consent"));
            postSMSDataRequest.setChannelId(resultSet.getString("Channel"));
            postSMSDataRequests.add(postSMSDataRequest);
        }
        return postSMSDataRequests;
    }

    /** ბაზიდან წამოღებული ინფორმაციის შენახვა ობიექტებად **/

    public static Object [][] postSMSRObjects (List<PostSMSDataRequest> postSMSDataRequests) throws SQLException {
        Object[][] data = new Object[postSMSDataRequests.size()][1];
        for (int i = 0; i <postSMSDataRequests.size() ; i++) {
            data[i][0] = postSMSDataRequests.get(i);
        }
        return data;
    }

    /** ბაზიდან წამოღებული ინფორმაციის შენახვა თითოეული ელემენტად **/

    @DataProvider(name = "postSMSRObjectsIndividual")
    public static Object [][] postSMSRObjectsIndividual (List<PostSMSDataRequest> postSMSDataRequestsList) throws SQLException {
        Object[][] data = new Object[postSMSDataRequestsList.size()][4];
        for (int i = 0; i <postSMSDataRequestsList.size() ; i++) {
            data[i][0] = postSMSDataRequestsList.get(i).getPersonId();
            data[i][1] = postSMSDataRequestsList.get(i).getTelNumber();
            data[i][2] = postSMSDataRequestsList.get(i).getStatus();
            data[i][3] = postSMSDataRequestsList.get(i).getChannelId();
        }
        return data;
    }
}
