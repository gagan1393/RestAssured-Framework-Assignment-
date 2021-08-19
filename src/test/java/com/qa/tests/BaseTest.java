package com.qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import org.json.simple.JSONObject;

import com.qa.utils.Constants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseTest {
	
	@BeforeMethod
	public void setUp() {
		
		RestAssured.baseURI = Constants.URL;
	}

	
	@Test(priority = 1)
	public void getRequestOfAllUsers() {
		
		given().log().all().
				header("Content-Type", "application/json").
					contentType("/application/json").log().all().
						when().log().all().
							get("Customers").
								then().log().all().
									assertThat().
										body("data.email[0]", equalTo("testerA@abc.com")).
 											body("data.email[1]", equalTo("testerB@abc.com")).
 												statusCode(Constants.RESPONSE_CODE_200);
											
										

	
	}
	
	@Test(priority = 2)
	public void getRequestOfSelectUser() {
		
	 given().log().all().
	 	contentType("application/json").log().all().
	 		when().log().all().
	 			get("/1111/CustomerView").
	 				then().log().all().
	 					assertThat().
	 						statusCode(Constants.RESPONSE_CODE_200).
	 							body("data.customerID", equalTo("1111")).
	 								body("data.first_name", equalTo("testerBFirst")).
	 									body("data.last_name", equalTo("testerBLast"));
	 
	}
	
	@Test(priority = 3)
	public void getRequestWithNoUserExist() {
		
	 given().log().all().
	 	contentType("application/json").log().all().
	 		when().log().all().
	 			get("/7777/CustomerView").
	 				then().log().all().
	 					assertThat().
	 						statusCode(Constants.RESPONSE_CODE_404);
	 													 
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority = 4)
	public void postRequest() {
		JSONObject object = new JSONObject();
		object.put("email", "abc@gmail.com");
		object.put("first_name", "Albert");
		object.put("last_name", "Einstein");
		
       System.out.println(object.toString());
		
		given().log().all().
			header("Content-Type", "application/json").
				contentType("application/json").log().all().
					accept(ContentType.JSON).
						body(object.toJSONString()).
							when().log().all().
								post("/Customers").
									then().log().all().
										assertThat().
											statusCode(Constants.RESPONSE_CODE_201);
							
							
			
	}

	
	@SuppressWarnings("unchecked")
	@Test(priority = 5)
	public void putRequest() {
		JSONObject object = new JSONObject();
		object.put("email", "abc@gmail.com");
		object.put("first_name", "Albert");
		object.put("last_name", "Einstein");
		
       System.out.println(object.toString());
		
       given().log().all().
		header("Content-Type", "application/json").
			contentType("application/json").log().all().
				accept(ContentType.JSON).
					body(object.toJSONString()).
						when().log().all().
							put("/Customers").
								then().log().all().
									assertThat().
										statusCode(Constants.RESPONSE_CODE_200);
							
							
			
	}

	
}
