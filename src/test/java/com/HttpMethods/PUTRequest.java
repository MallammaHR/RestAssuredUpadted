package com.HttpMethods;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PUTRequest 
{
	@Test
	public static void putRequestVerifyStatusCode()
	{
		JSONObject request=new JSONObject();
		request.put("name","Rooooooooooopa");
		request.put("job", "Teacher");
		
		RestAssured.baseURI="https://reqres.in/api/";
		
		given()
			.log().all()
			.header("Contetnt-Type","application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString()).
		when()
			.put("users").
		then()
			.assertThat().statusCode(201).log().all();
			
		
	}
}
