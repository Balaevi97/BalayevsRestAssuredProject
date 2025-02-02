
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FirstLab {

    @Test
    public void firsLabExercises () {

            Response getData = RestAssured.get("https://reqres.in/api/users?page=2");
            Assert.assertEquals(getData.statusCode(), 200);
            Assert.assertEquals(getData.getStatusCode(), 200);
            System.out.println(getData.jsonPath().getList("data").size());
            System.out.println(getData.jsonPath().getString("data.email" ));
            Assert.assertTrue(getData.jsonPath().getString(" data.email" ).contains("@"));
            Assert.assertTrue(getData.getHeader("Content-type").contains("application/json"));

    }
}
