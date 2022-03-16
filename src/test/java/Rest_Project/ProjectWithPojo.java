package Rest_Project;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pojo.pojoClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectWithPojo {
	
	@Test
	public void CreateUser(ITestContext var) throws JsonProcessingException {
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		pojoClass pojo = new pojoClass();
		pojo.setUsername("priya");
		pojo.setFirstName("shawn");
		pojo.setLastName("thomas");
		pojo.setPhone("12345");
		pojo.setEmail("abc.abc");
		pojo.setUserStatus(0);
		pojo.setPassword("xyz");
		
		System.out.println(pojo.getUsername());
		System.out.println(pojo.getFirstName());
		System.out.println(pojo.getLastName());
		System.out.println(pojo.getPassword());
		System.out.println(pojo.getUserStatus());
		System.out.println(pojo.getPhone());
		System.out.println(pojo.getEmail());
		
		ObjectMapper obj=new ObjectMapper();
		String body = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		System.out.println(body);
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		RestAssured.given().contentType(ContentType.JSON).body(body).when().post("/user").then().statusCode(200).log().all();
		var.setAttribute("username", pojo.getUsername());
		var.setAttribute("password", pojo.getPassword());
	}
	
	@Test
	public void ModifyUser(ITestContext var) throws JsonProcessingException {
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String user = var.getAttribute("username").toString();
		pojoClass pojo = new pojoClass();
		pojo.setUsername(user);
		pojo.setFirstName("shawn");
		pojo.setLastName("thomas");
		pojo.setPhone("12345");
		pojo.setEmail("abc.abc");
		pojo.setUserStatus(0);
		pojo.setPassword("xyz");
		ObjectMapper obj=new ObjectMapper();
		String body = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		Response res = RestAssured.given().contentType(ContentType.JSON)
		.body(body)
		.when().put("/user/user").then().statusCode(200).log().all().extract().response();
		
		}

	
	
	  @Test public void LoginUser(ITestContext var) {
	  
	  
		  RestAssured.baseURI="https://petstore.swagger.io/v2"; String user =
				  var.getAttribute("username").toString(); String pass =
				  var.getAttribute("password").toString();

	  
	  RestAssured.given() .queryParam("username",user).queryParam("password",pass)
	  .when().get("/user/login").then().statusCode(200).log().all().extract().
	  response();
	  
	  }
	  
	  @Test public void LogoutUser() {
	  
	  
	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  RestAssured.given().when().get("/user/logout").then().statusCode(200).log().
	  all().extract().response();
	  
	  }
	  
	  @Test public void DeleteUser(ITestContext var) {
	  
	  String user = var.getAttribute("username").toString();
	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  RestAssured.given().when().get("/user/"+user).then().statusCode(200).log().
	  all().extract().response();
	  
	  }
	 

}
