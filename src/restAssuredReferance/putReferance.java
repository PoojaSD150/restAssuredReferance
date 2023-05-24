package restAssuredReferance;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class putReferance {

    public static void main(String[] args) {

        // Step 1: Declare Base URL
        RestAssured.baseURI = "https://reqres.in/";

        // Step 2.1: Configure Request Body ,save in local variable
        String requestBody = "{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\"\r\n"
        		+ "}";
        //System.out.println(requestBody);
        
        // Step 2.2: extract the request body parameter
         JsonPath jspRequest = new JsonPath(requestBody);
         String req_name= jspRequest.getString("name");
         String req_job= jspRequest.getString("job");
         
         //step3.1: configure the request body
         int statusCode=given().header("Content-Type","application/json").body(requestBody).when().put("api/users/2").then().extract().statusCode();
         
         String responseBody=given().header("Content-Type","application/json").body(requestBody).when().put("api/users/2").then().extract().asString();
         
    
        // Step 3.2: Parse the response body
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_updatedAt = jsp.getString("updatedAt");

        
         // Step 3.3: Validate the response body parameters
        
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(res_name, req_name);
        Assert.assertEquals(res_job, req_job);
         Assert.assertNotNull(res_updatedAt);
        
        //validate updatedAt date
        String expectedDate = new java.util.Date().toInstant().toString().substring(0, 10);
        String actualDate = res_updatedAt.substring(0, 10);
        Assert.assertEquals(actualDate, expectedDate);
        

    	System.out.println("Status code is: "+ statusCode + " Created");
    
    	System.out.println(responseBody);
    	System.out.println("date-" +expectedDate);
        
    }
}