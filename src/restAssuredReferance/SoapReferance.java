package restAssuredReferance;

import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class SoapReferance {

	public static void main(String[] args) {
		// step1:declare baseURl and request body variables
		String BaseURI = "https://www.dataaccess.com";
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		//step2:Fetch request Body
		XmlPath xmlrequest = new XmlPath(requestBody);
		String req_param= xmlrequest.getString("ubiNum");
		//System.out.println(req_param);
		
		//step3:configure the API and fetch response body
		RestAssured.baseURI = BaseURI;
		String responseBody = given().header("Content-Type","text/xml; charset=utf-8").body(requestBody).when().post("/webservicesserver/NumberConversion.wso")
				.then().extract().response().getBody().asString();
		System.out.println(responseBody);
		
        //step4:parse the responseBOdy and and response parameters
		XmlPath xml_res=new XmlPath(responseBody);
		String Result = xml_res.getString("NumberToWordsResult");
		//System.out.println(Result);
		
		//step5:validate response body parameters

	}

}
