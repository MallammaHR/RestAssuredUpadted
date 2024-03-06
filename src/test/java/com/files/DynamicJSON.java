package com.files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSON 
{
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp=given().log().all()
			.header("Content-Type","application/json")
			.body(Payload.AddBooks(isbn,aisle)).
		when()
			.post("/Library/Addbook.php").
		then()
			.log().all().assertThat().statusCode(200).extract().response().asString();
		
		//to extract String form Json object we have JsonPath-to extract value string from  JSON Object
		JsonPath js=ReUsableMethods.rawToJson(resp);
		String id=js.get("ID");
		System.out.println("Id value is : "+id);	
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new  Object[][] {{"ddfdjks","475657"},{"hzczjxcz","57345"},{"fhdskfhsd","579476"}};
		
	}
}
