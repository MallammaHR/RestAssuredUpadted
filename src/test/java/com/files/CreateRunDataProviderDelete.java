package com.files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateRunDataProviderDelete
{
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{	//add book
		RestAssured.baseURI="http://216.10.245.166";
		System.out.println("************************************************************************");
		String resp=given().log().all()
			.header("Content-Type","application/json")
			.body(Payload.AddBooks(isbn,aisle)).
		when()
			.post("/Library/Addbook.php").
		then()
			.log().all().assertThat().statusCode(200).extract().response().asString();
		
		//to extract String form Json object we have JsonPath-to extract value string from  JSON Object
		JsonPath js=ReUsableMethods.rawToJson(resp);
		String ID=js.get("ID");
		System.out.println("Id value is : "+ID);	
		
		//delete book
		given()
		
			.log().all().header("Content-Type","application/json")
			.body(Payload.DeleteBook(ID)).
		when()
			.delete("/Library/Addbook.php").
		then()
			.log().all().assertThat().statusCode(200);
	
		System.out.println("ID is deleted : "+ID);
		System.out.println("************************************************************************");

		
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new  Object[][] {{"aaa","444"},{"bbb","555"},{"ccc","666"}};
		
	}
}
